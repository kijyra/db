package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.controller.MainController;
import ru.dallari.db.entity.*;
import ru.dallari.db.repository.LocationRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping(path = "/edit/locations/")
    public String locations(Model model) {
        model.addAttribute("title", "Расположения");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());

        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "edit/locations/main";
    }


    @GetMapping(path = "/edit/locations/add/")
    public String addLocation(Model model){
        model.addAttribute("title", "Добавить расположение");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        return "edit/locations/add";
    }

    @PostMapping(path = "/edit/locations/add/")
    public String addLocation(@RequestParam String name, Model model){
        try {
            locationRepository.save(new Location(name));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/locations/";
        }
    }

    @GetMapping(path = "/edit/locations/{id}/")
    public String editLocation(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("title", "Редактирование расположение");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());

        if(!locationRepository.existsById(id)){
            return "redirect:/edit/locations/";
        }

        Iterable<Computer> computers = locationRepository.findById(id).get().getComputerList();
        model.addAttribute("computers", computers);
        Iterable<Printer> printers = locationRepository.findById(id).get().getPrinterList();
        model.addAttribute("printers", printers);
        Iterable<Phone> phones = locationRepository.findById(id).get().getPhoneList();
        model.addAttribute("phones", phones);

        Optional<Location> location = locationRepository.findById(id);
        ArrayList<Location> res = new ArrayList<>();
        location.ifPresent(res::add);
        model.addAttribute("location", res);
        return "edit/locations/edit";
    }

    @PostMapping(path = "/edit/locations/{id}/")
    public String locationUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, Model model){
        Location location = locationRepository.findById(id).orElseThrow();
        location.setName(name);
        try {
            locationRepository.save(location);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/locations/";
        }
    }
}
