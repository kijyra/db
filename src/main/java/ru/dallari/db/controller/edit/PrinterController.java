package ru.dallari.db.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dallari.db.entity.Location;
import ru.dallari.db.entity.Printer;
import ru.dallari.db.entity.PrinterModel;
import ru.dallari.db.entity.User;
import ru.dallari.db.repository.LocationRepository;
import ru.dallari.db.repository.PrinterModelRepository;
import ru.dallari.db.repository.PrinterRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PrinterController {
    @Autowired
    PrinterRepository printerRepository;
    @Autowired
    PrinterModelRepository printerModelRepository;
    @Autowired
    LocationRepository locationRepository;

    @GetMapping(path = "/edit/printers/")
    public String printers(Model model) {
        model.addAttribute("title", "Принтеры");

        Iterable<Printer> printers = printerRepository.findAll();
        model.addAttribute("printers", printers);
        return "edit/printers/main";
    }

    @GetMapping(path = "/edit/printers/add/")
    public String addPrinter(Model model){
        model.addAttribute("title", "Добавить принтер");
        Iterable<PrinterModel> printerModels = printerModelRepository.findAll();
        model.addAttribute("printerModel", printerModels);
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "edit/printers/add";
    }

    @PostMapping(path = "/edit/printers/add/")
    public String addPrinter(
            @RequestParam String dnsname,
            @RequestParam String IP,
            @RequestParam String countPrinter,
            @RequestParam String countScanner,
            @RequestParam String location,
            @RequestParam String printerModel,
            Model model
    ){

        Location locate = locationRepository.findLocationByName(location).getFirst();
        PrinterModel pModel = printerModelRepository.findPrinterModelByName(printerModel).getFirst();
        if (((countPrinter.replaceAll("\\D+", "")) == "") || countPrinter == ""){
            countPrinter = "0";
        }
        if (((countScanner.replaceAll("\\D+", "")) == "") || countScanner == ""){
            countScanner = "0";
        }
        try {
            printerRepository.save(new Printer(
                    dnsname,
                    IP,
                    Integer.valueOf(countPrinter.replaceAll("\\D+", "")),
                    Integer.valueOf(countScanner.replaceAll("\\D+", "")),
                    pModel,
                    locate));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/printers/";
        }
    }

    @GetMapping(path = "/edit/printers/{id}/")
    public String editPrinter(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("title", "Редактирование принтера");
        Iterable<PrinterModel> printerModels = printerModelRepository.findAll();
        model.addAttribute("printerModel", printerModels);
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("location", locations);

        if(!printerRepository.existsById(id)){
            return "redirect:/edit/printers/";
        }

        Iterable<User> users = printerRepository.findById(id).get().getUserList();
        model.addAttribute("users", users);
        Optional<Printer> printer = printerRepository.findById(id);
        ArrayList<Printer> res = new ArrayList<>();
        printer.ifPresent(res::add);
        model.addAttribute("printer", res);
        return "edit/printers/edit";
    }

    @PostMapping(path = "/edit/printers/{id}/")
    public String printerUpdate(
            @PathVariable(value = "id") Long id,
            @RequestParam String dnsname,
            @RequestParam String IP,
            @RequestParam String countPrinter,
            @RequestParam String countScanner,
            @RequestParam String location,
            @RequestParam String printerModel
    ){
        Location loc = locationRepository.findLocationByName(location).getFirst();
        PrinterModel pModel = printerModelRepository.findPrinterModelByName(printerModel).getFirst();
        Printer printer = printerRepository.findById(id).orElseThrow();
        printer.setDNSname(dnsname);
        printer.setIP(IP);
        if (((countPrinter.replaceAll("\\D+", "")) == "") || countPrinter == ""){
            countPrinter = "0";
        }
        if (((countScanner.replaceAll("\\D+", "")) == "") || countScanner == ""){
            countScanner = "0";
        }
        printer.setPrintCount(Integer.valueOf(countPrinter.replaceAll("\\D+", "")));
        printer.setScanCount(Integer.valueOf(countScanner.replaceAll("\\D+", "")));
        printer.setLocation(loc);
        printer.setPrinterModel(pModel);

        try {
            printerRepository.save(printer);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return "redirect:/edit/printers/";
        }
    }

    @GetMapping(path = "/edit/printers/{id}/scanUpdate/")
    public String scanUpdate(@PathVariable(value = "id") Long id, Model model) throws IOException {
        Printer printer = printerRepository.findById(id).orElseThrow();
        String response = printer.getPrinterSNMP("ScannerCounter");
        if (response != null) {
            printer.setScanCount(Integer.valueOf(response));
            printerRepository.save(printer);
        } else {
            System.out.println("Error getting value scanner counter via SMTP for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }
        return "redirect:/edit/printers/{id}/";
    }

    @GetMapping(path = "/edit/printers/{id}/printUpdate/")
    public String printUpdate(@PathVariable(value = "id") Long id, Model model) throws IOException {
        Printer printer = printerRepository.findById(id).orElseThrow();
        String response = printer.getPrinterSNMP("PrinterCounter");
        if (response != null) {
            printer.setPrintCount(Integer.valueOf(response));
            printerRepository.save(printer);
        } else {
            System.out.println("Error getting value printer counter via SMTP for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }
        return "redirect:/edit/printers/{id}/";
    }

    @GetMapping(path = "/edit/printers/{id}/hostnameUpdate/")
    public String hostnameUpdate(@PathVariable(value = "id") Long id, Model model) throws IOException {
        Printer printer = printerRepository.findById(id).orElseThrow();
        String response = printer.getPrinterSNMP("hostname");
        if (response != null) {
            printer.setDNSname(response);
            printerRepository.save(printer);
        } else {
            System.out.println("Error getting value hostname via SMTP for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }
        return "redirect:/edit/printers/{id}/";
    }

    @GetMapping(path = "/edit/printers/{id}/update/")
    public String Update(@PathVariable(value = "id") Long id, Model model) throws IOException {
        Printer printer = printerRepository.findById(id).orElseThrow();
        String hostname = printer.getPrinterSNMP("hostname");
        String printerCounter = printer.getPrinterSNMP("PrinterCounter");
        String scannerCounter = printer.getPrinterSNMP("ScannerCounter");
        String modelName = printer.getPrinterSNMP("Model");
        if (hostname != null) {
            printer.setDNSname(hostname);
        } else {
            System.out.println("Error getting value hostname via SMTP for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }
        if (printerCounter != null) {
            printer.setPrintCount(Integer.valueOf(printerCounter));
        } else {
            System.out.println("Error getting value printer counter via SMTP for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }
        if (scannerCounter != null) {
            printer.setScanCount(Integer.valueOf(scannerCounter));
        } else {
            System.out.println("Error getting value scanner counter via SMTP for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }
        if (modelName != null) {
            PrinterModel printerModel = printerModelRepository.findPrinterModelByName(modelName).getFirst();
            printer.setPrinterModel(printerModel);
        } else {
            System.out.println("Error getting value printerModel from DataBase for printer " + printer.getPrinterModel().getName() + ". ID: " + printer.getId());
        }

        printerRepository.save(printer);
        return "redirect:/edit/printers/";
    }

}
