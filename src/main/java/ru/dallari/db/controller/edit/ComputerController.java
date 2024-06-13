package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.controller.MainController;
import ru.dallari.db.entity.Computer;
import ru.dallari.db.entity.Location;
import ru.dallari.db.entity.User;
import ru.dallari.db.repository.ComputerRepository;
import ru.dallari.db.repository.LocationRepository;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ComputerController {
    @Autowired
    ComputerRepository computerRepository;
    @Autowired
    LocationRepository locationRepository;

    @GetMapping(path = "/edit/computers/")
    public String computers(Model model) {
        model.addAttribute("computers", "true");
        model.addAttribute("title", "Компьютеры");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        Iterable<Computer> computers = computerRepository.findAll();
        model.addAttribute("computers", computers);

        return "edit/computers/main";
    }

    @GetMapping(path = "/edit/computers/add/")
    public String addComputer(Model model){
        model.addAttribute("computers", "true");
        model.addAttribute("title", "Добавить компьютер");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "edit/computers/add";
    }

    @PostMapping(path = "/edit/computers/add/")
    public String addComputer(
            @RequestParam String DNSname,
            @RequestParam(required=false) Boolean domain,
            @RequestParam(required=false) Boolean thinClient,
            @RequestParam String IP,
            @RequestParam String office,
            @RequestParam String location,
            Model model
    ){
        System.out.println(domain);
        Location locate = locationRepository.findLocationByName(location).getFirst();
        try {
            computerRepository.save(new Computer(
                    DNSname,
                    domain,
                    thinClient,
                    IP,
                    office,
                    locate));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/computers/";
        }
    }

    @GetMapping(path = "/edit/computers/{id}/")
    public String editComputer(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("computers", "true");
        model.addAttribute("title", "Редактирование компьютера");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("location", locations);
        if(!computerRepository.existsById(id)){
            return "redirect:/edit/computers/";
        }
        Iterable<User> users = computerRepository.findById(id).get().getUserList();
        model.addAttribute("users", users);
        Optional<Computer> computer = computerRepository.findById(id);
        ArrayList<Computer> res = new ArrayList<>();
        computer.ifPresent(res::add);
        model.addAttribute("computer", res);
        return "edit/computers/edit";
    }

    @PostMapping(path = "/edit/computers/{id}/")
    public String computerUpdate(
            @PathVariable(value = "id") Long id,
            @RequestParam String DNSname,
            @RequestParam(required=false) Boolean domain,
            @RequestParam(required=false) Boolean thinClient,
            @RequestParam String IP,
            @RequestParam String office,
            @RequestParam String location,
            Model model
    ){
        Location loc = locationRepository.findLocationByName(location).getFirst();
        Computer computer = computerRepository.findById(id).orElseThrow();
        computer.setDNSname(DNSname);
        computer.setDomain(domain);
        computer.setIP(IP);
        computer.setOffice(office);
        computer.setLocation(loc);
        computer.setThinClient(thinClient);

        try {
            computerRepository.save(computer);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/computers/";
        }
    }

    @GetMapping(path = "/edit/computers/{id}/getDNSname/")
    public String DNSnameUpdate(@PathVariable(value = "id") Long id, Model model) throws IOException {
        Computer computer = computerRepository.findById(id).orElseThrow();
        model.addAttribute("title", "Компьютеры");
        model.addAttribute("currentUsername", MainController.currentUserName());
        model.addAttribute("currentRole", MainController.currentRole());
        model.addAttribute("computers", "true");
        try {
            InetAddress addr = InetAddress.getByName(computer.getIP());
            String hostname = addr.getHostName().strip();
            if(computer.getDNSname() != hostname) {
                computer.setDNSname(hostname);
                computerRepository.save(computer);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return "redirect:/edit/computers/";
    }

}
