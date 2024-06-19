package ru.dallari.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dallari.db.config.SecurityConfig;
import ru.dallari.db.entity.*;
import ru.dallari.db.repository.AuthUserRepository;
import ru.dallari.db.repository.UserRepository;
import ru.dallari.db.service.AuthUserDetailService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    private static Authentication currentAuth;

    public static String currentUserName(){
        currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth.getName().equals("anonymousUser")) {
            return "anonymous";
        }
        return currentAuth.getName();
    }
    public static String currentRole(){
        currentAuth = SecurityContextHolder.getContext().getAuthentication();
        return currentAuth
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }


    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("currentUsername", currentUserName());
        model.addAttribute("currentRole", currentRole());
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping(path = "/files/")
    public String files(Model model) {
        model.addAttribute("title", "Загрузки");
        model.addAttribute("currentUsername", currentUserName());
        model.addAttribute("currentRole", currentRole());
        return "protocols/files";
    }

    @GetMapping(path = "/download/")
    public String download(Model model) {
        model.addAttribute("title", "Загрузки");
        model.addAttribute("currentUsername", currentUserName());
        model.addAttribute("currentRole", currentRole());
        return "download";
    }

    @GetMapping(path = "/view/")
    public String detailView(Model model) {
        model.addAttribute("title", "Просмотр");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "view/view";
    }
    @GetMapping(path = "/view/{id}/")
    public String view(@PathVariable(value = "id") Integer id, Model model) throws UnknownHostException {
        model.addAttribute("title", "Детальный просмотр");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        User user = userRepository.findById(id).orElseThrow();

        Iterable<Computer> computers = user.getComputers();
        model.addAttribute("pcIsOnline", isOnline(computers.iterator().next().getIP()));

        Iterable<Printer> printers = user.getPrinters();
        model.addAttribute("prIsOnline", isOnline(printers.iterator().next().getIP()));

        Iterable<Phone> phones = user.getPhones();
        model.addAttribute("phIsOnline", isOnline(phones.iterator().next().getIP()));

        model.addAttribute("user", user);
        return "view/detailView";
    }

    private boolean isOnline(String ip) {
        boolean isOnline;
        try {
            InetAddress inet = InetAddress.getByName(ip);
            isOnline = inet.isReachable(500);
        } catch (UnknownHostException e) {
            System.out.println("Host is unknown");
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isOnline;
    }

}
