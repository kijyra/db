package ru.dallari.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
public class Cartridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "cartridge")
    private List<PrinterModel> printerModelList;

    public Cartridge() {
    }

    public Cartridge(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PrinterModel> getPrinterModelList() {
        return printerModelList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinterModelList(List<PrinterModel> printerModelList) {
        this.printerModelList = printerModelList;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
