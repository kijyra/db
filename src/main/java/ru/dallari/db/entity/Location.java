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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;

    @OneToMany(mappedBy = "location")
    private List<Phone> phoneList;
    @OneToMany(mappedBy = "location")
    private List<Printer> printerList;
    @OneToMany(mappedBy = "location")
    private List<Computer> computerList;

    public Location() {
    }
    public Location(String name){
        this.name = name;
    }
}
