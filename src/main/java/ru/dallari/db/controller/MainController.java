package ru.dallari.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dallari.db.entity.*;
import ru.dallari.db.repository.UserRepository;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping(path = "/view/")
    public String detailView(Model model) {
        model.addAttribute("title", "Просмотр");
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "view/view";
    }
    @GetMapping(path = "/view/{id}/")
    public String view(@PathVariable(value = "id") Integer id, Model model) throws UnknownHostException {
        model.addAttribute("title", "Детальный просмотр");
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
