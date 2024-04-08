package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.entity.Cartridge;
import ru.dallari.db.entity.Printer;
import ru.dallari.db.entity.PrinterModel;
import ru.dallari.db.repository.CartridgeRepository;
import ru.dallari.db.service.AuthUserDetailService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CartridgeController {
    @Autowired
    private CartridgeRepository cartridgeRepository;


    @GetMapping(path = "/edit/cartridges/")
    public String cartridges(Model model) {
        model.addAttribute("title", "Картриджи");

        Iterable<Cartridge> cartridges = cartridgeRepository.findAll();
        model.addAttribute("cartridges", cartridges);
        return "edit/cartridges/main";
    }


    @GetMapping(path = "/edit/cartridges/add/")
    public String addCartridge(Model model){
        model.addAttribute("title", "Добавить картридж");
        return "edit/cartridges/add";
    }

    @PostMapping(path = "/edit/cartridges/add/")
    public String addCartridge(@RequestParam String name, Model model){
        try {
            cartridgeRepository.save(new Cartridge(name));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/cartridges/";
        }
    }

    @GetMapping(path = "/edit/cartridges/{id}/")
    public String editCartridge(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("title", "Редактирование картридж");

        if(!cartridgeRepository.existsById(id)){
            return "redirect:/edit/cartridges/";
        }
        Iterable<PrinterModel> models = cartridgeRepository.findById(id).get().getPrinterModelList();
        model.addAttribute("models", models);
        Optional<Cartridge> cartridge = cartridgeRepository.findById(id);
        ArrayList<Cartridge> res = new ArrayList<>();
        cartridge.ifPresent(res::add);
        model.addAttribute("cartridge", res);
        return "edit/cartridges/edit";
    }

    @PostMapping(path = "/edit/cartridges/{id}/")
    public String cartridgeUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, Model model, Principal principal){
        Cartridge cartridge = cartridgeRepository.findById(id).orElseThrow();
        model.addAttribute("username", principal.getName());
        try {
            cartridgeRepository.save(cartridge);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/cartridges/";
        }
    }

}
