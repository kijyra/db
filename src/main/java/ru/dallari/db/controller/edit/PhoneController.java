package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.entity.Location;
import ru.dallari.db.entity.Phone;
import ru.dallari.db.entity.User;
import ru.dallari.db.repository.LocationRepository;
import ru.dallari.db.repository.PhoneRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PhoneController {
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    LocationRepository locationRepository;

    @GetMapping(path = "/edit/phones/")
    public String printers(Model model) {
        model.addAttribute("title", "Телефоны");

        Iterable<Phone> phones = phoneRepository.findAll();
        model.addAttribute("phones", phones);
        return "edit/phones/main";
    }

    @GetMapping(path = "/edit/phones/add/")
    public String addPrinter(Model model){
        model.addAttribute("title", "Добавить телефон");
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "edit/phones/add";
    }

    @PostMapping(path = "/edit/phones/add/")
    public String addPrinter(
            @RequestParam String IP,
            @RequestParam String modelPhone,
            @RequestParam String number,
            @RequestParam String location,
            Model model
    ){
        Location locate = locationRepository.findLocationByName(location).getFirst();
        if (((number.replaceAll("\\D+", "")) == "") || number == ""){
            number = "0";
        }
        try {
            phoneRepository.save(new Phone(
                    IP,
                    modelPhone,
                    Integer.valueOf(number),
                    locate));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/phones/";
        }
    }

    @GetMapping(path = "/edit/phones/{id}/")
    public String editPhone(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("title", "Редактирование телефона");
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("location", locations);

        if(!phoneRepository.existsById(id)){
            return "redirect:/edit/phones/";
        }

        Iterable<User> users = phoneRepository.findById(id).get().getUserList();
        model.addAttribute("users", users);
        Optional<Phone> phone = phoneRepository.findById(id);
        ArrayList<Phone> res = new ArrayList<>();
        phone.ifPresent(res::add);
        model.addAttribute("phone", res);
        return "edit/phones/edit";
    }

    @PostMapping(path = "/edit/phones/{id}/")
    public String phoneUpdate(
            @PathVariable(value = "id") Long id,
            @RequestParam String IP,
            @RequestParam String modelPhone,
            @RequestParam String number,
            @RequestParam String location,
            Model model
    ){
        Location loc = locationRepository.findLocationByName(location).getFirst();
        Phone phone = phoneRepository.findById(id).orElseThrow();
        phone.setIP(IP);
        phone.setModel(modelPhone);
        if (((number.replaceAll("\\D+", "")) == "") || number == ""){
            number = "0";
        }

        phone.setNumber(Integer.valueOf(number.replaceAll("\\D+", "")));
        phone.setLocation(loc);

        try {
            phoneRepository.save(phone);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/phones/";
        }
    }
}
