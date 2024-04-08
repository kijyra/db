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
import ru.dallari.db.entity.User;
import ru.dallari.db.repository.CartridgeRepository;
import ru.dallari.db.repository.PrinterModelRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PrinterModelController {
    @Autowired
    private PrinterModelRepository printerModelRepository;
    @Autowired
    private CartridgeRepository cartridgeRepository;

    @GetMapping(path = "/edit/printerModels/")
    public String printerModels(Model model) {
        model.addAttribute("title", "Модели принтеров");

        Iterable<PrinterModel> printerModels = printerModelRepository.findAll();
        model.addAttribute("printerModels", printerModels);
        return "edit/printerModels/main";
    }


    @GetMapping(path = "/edit/printerModels/add/")
    public String addPrinterModel(Model model){
        model.addAttribute("title", "Добавить модель принтера");
        Iterable<Cartridge> cartridges = cartridgeRepository.findAll();
        model.addAttribute("cartridges", cartridges);
        return "edit/printerModels/add";
    }

    @PostMapping(path = "/edit/printerModels/add/")
    public String addPrinterModel(@RequestParam String name, @RequestParam String manufacturer, @RequestParam String cartridge, Model model){

        Cartridge cart = cartridgeRepository.findCartridgeByName(cartridge).getFirst();

        try {
            printerModelRepository.save(new PrinterModel(name, manufacturer, cart));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/printerModels/";
        }
    }

    @GetMapping(path = "/edit/printerModels/{id}/")
    public String editPrinterModel(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("title", "Редактирование модель принтера");
        Iterable<Cartridge> cartridges = cartridgeRepository.findAll();
        model.addAttribute("cartridges", cartridges);
        if(!printerModelRepository.existsById(id)){
            return "redirect:/edit/printerModels/";
        }
        Iterable<Printer> printers = printerModelRepository.findById(id).get().getPrinterList();
        model.addAttribute("printers", printers);
        Optional<PrinterModel> printerModel = printerModelRepository.findById(id);
        ArrayList<PrinterModel> res = new ArrayList<>();
        printerModel.ifPresent(res::add);
        model.addAttribute("printerModel", res);
        return "edit/printerModels/edit";
    }

    @PostMapping(path = "/edit/printerModels/{id}/")
    public String printerModelUpdate(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String manufacturer, @RequestParam String cartridge, Model model){
        Cartridge cart = cartridgeRepository.findCartridgeByName(cartridge).getFirst();
        PrinterModel printerModel = printerModelRepository.findById(id).orElseThrow();
        printerModel.setName(name);
        printerModel.setManufacturer(manufacturer);
        printerModel.setCartridge(cart);
        try {
            printerModelRepository.save(printerModel);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/printerModels/";
        }
    }
}
