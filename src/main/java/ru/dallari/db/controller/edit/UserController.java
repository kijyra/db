package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.entity.*;
import ru.dallari.db.repository.*;

import java.util.*;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ComputerRepository computerRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    PrinterRepository printerRepository;

    @GetMapping(path = "/edit/users/")
    public String users(Model model) {
        model.addAttribute("title", "Пользователи");
        model.addAttribute("authUser", "admin");
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "edit/users/main";
    }

    @GetMapping(path = "/edit/users/add/")
    public String addUser(Model model){
        model.addAttribute("title", "Добавить пользователя");
        model.addAttribute("userTab", "true");
        Iterable<Computer> computers = computerRepository.findAll();
        model.addAttribute("computers", computers);
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        Iterable<Phone> phones = phoneRepository.findAll();
        model.addAttribute("phones", phones);
        Iterable<Printer> printers = printerRepository.findAll();
        model.addAttribute("printers", printers);
        return "edit/users/add";
    }

    @PostMapping(path = "/edit/users/add/")
    public String addUser(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String bitrix,
            @RequestParam String computer,
            @RequestParam String department,
            @RequestParam String phone,
            @RequestParam String printer,
            Model model
    ){
        Department dp = departmentRepository.findDepartmentByName(department).getFirst();

        Computer pc = computerRepository.findComputerByIP(computer).getFirst();
        Phone ph = phoneRepository.findPhoneByIP(phone).getFirst();
        Printer pr = printerRepository.findPrinterByIP(printer).getFirst();

        List<Computer> computers = new ArrayList<>();
        computers.add(pc);
        List<Phone> phones = new ArrayList<>();
        phones.add(ph);
        List<Printer> printers = new ArrayList<>();
        printers.add(pr);

        try {
            userRepository.save(new User(
                    name,
                    surname,
                    bitrix,
                    computers,
                    dp,
                    phones,
                    printers));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/users/";
        }
    }

    @GetMapping(path = "/edit/users/{id}/")
    public String editUser(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("userTab", "true");
        model.addAttribute("title", "Редактирование пользователя");

        Iterable<Computer> computers = computerRepository.findAll();
        model.addAttribute("computers", computers);
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        Iterable<Phone> phones = phoneRepository.findAll();
        model.addAttribute("phones", phones);
        Iterable<Printer> printers = printerRepository.findAll();
        model.addAttribute("printers", printers);

        if(!userRepository.existsById(id)){
            return "redirect:/edit/users/";
        }

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "edit/users/edit";
    }

    @PostMapping(path = "/edit/users/{id}/")
    public String userUpdate(
            @PathVariable(value = "id") Integer id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String bitrix,
            @RequestParam String computer,
            @RequestParam String department,
            @RequestParam String phone,
            @RequestParam String printer,
            Model model
    ){
        Department dp = departmentRepository.findDepartmentByName(department).getFirst();

        Computer pc = computerRepository.findComputerByIP(computer).getFirst();
        Phone ph = phoneRepository.findPhoneByIP(phone).getFirst();
        Printer pr = printerRepository.findPrinterByIP(printer).getFirst();

        List<Computer> computers = new ArrayList<>();
        computers.add(pc);
        List<Phone> phones = new ArrayList<>();
        phones.add(ph);
        List<Printer> printers = new ArrayList<>();
        printers.add(pr);

        User user = userRepository.findById(id).orElseThrow();
        user.setName(name);
        user.setSurname(surname);
        user.setBitrix(bitrix);
        user.setComputers(computers);
        user.setDepartment(dp);
        user.setPhones(phones);
        user.setPrinters(printers);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/users/";
        }
    }
}
