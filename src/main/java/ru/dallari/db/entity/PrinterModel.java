package ru.dallari.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class PrinterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String manufacturer, name;
    @ManyToOne
    private Cartridge cartridge;
    @OneToMany(mappedBy = "printerModel")
    private List<Printer> printerList;

    public PrinterModel() {

    }

    public PrinterModel(String name, String manufacturer, Cartridge cartridge) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.cartridge = cartridge;
    }

}
