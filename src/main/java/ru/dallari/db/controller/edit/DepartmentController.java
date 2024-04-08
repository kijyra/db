package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.entity.Department;
import ru.dallari.db.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping(path = "/edit/departments/")
    public String departments(Model model) {
        model.addAttribute("title", "Отделы");

        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "edit/departments/main";
    }


    @GetMapping(path = "/edit/departments/add/")
    public String addDepartment(Model model){
        model.addAttribute("title", "Добавить отдел");
        return "edit/departments/add";
    }

    @PostMapping(path = "/edit/departments/add/")
    public String addDepartment(@RequestParam String name, Model model){
        try {
            departmentRepository.save(new Department(name));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/departments/";
        }
    }

    @GetMapping(path = "/edit/departments/{id}/")
    public String editCartridge(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("title", "Редактирование отдела");

        if(!departmentRepository.existsById(id)){
            return "redirect:/edit/departments/";
        }
        Optional<Department> department = departmentRepository.findById(id);
        ArrayList<Department> res = new ArrayList<>();
        department.ifPresent(res::add);
        model.addAttribute("department", res);
        return "edit/departments/edit";
    }

    @PostMapping(path = "/edit/departments/{id}/")
    public String cartridgeUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, Model model){
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setName(name);
        try {
            departmentRepository.save(department);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/departments/";
        }
    }
}
