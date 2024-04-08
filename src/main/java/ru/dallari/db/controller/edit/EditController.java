package ru.dallari.db.controller.edit;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dallari.db.config.AuthUserDetails;
import ru.dallari.db.entity.AuthUser;
import ru.dallari.db.service.AuthUserDetailService;


@Controller
public class EditController {
    AuthUserDetails authUserDetails;

    @GetMapping(path = "/edit/")
    public String edit(Model model) {
        model.addAttribute("title", "Редактирование");
        return "redirect:/edit/users/";
    }


}
