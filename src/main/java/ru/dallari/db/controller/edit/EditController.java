package ru.dallari.db.controller.edit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dallari.db.controller.MainController;

@Controller
public class EditController {

    @GetMapping(path = "/edit/")
    public String edit(Model model) {
        model.addAttribute("title", "Редактирование");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        return "redirect:/edit/users/";
    }


}
