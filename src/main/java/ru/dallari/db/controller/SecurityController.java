package ru.dallari.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.entity.AuthUser;
import ru.dallari.db.entity.Department;
import ru.dallari.db.repository.AuthUserRepository;

import java.util.stream.Collectors;

@Controller
public class SecurityController {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping(path = "/user")
    public String user(Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        return "user";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("title", "Регистрация аккаунта");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String password){
        AuthUser authUser = new AuthUser(username, passwordEncoder.encode(password), "ROLE_USER");
        System.out.println(authUser.getUsername() + " " + authUser.getPassword());
        try {
            authUserRepository.save(authUser);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/login";
        }
    }
}
