package ru.dallari.db.controller.edit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditController {

    @GetMapping(path = "/edit/")
    public String edit(Model model) {
        model.addAttribute("title", "Редактирование");
        return "redirect:/edit/users/";
    }


}
