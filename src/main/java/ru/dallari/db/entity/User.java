package ru.dallari.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, surname, bitrix;
    @ManyToMany
    private List<Computer> computers = new ArrayList<Computer>();
    @ManyToOne
    private Department department;
    @ManyToMany
    private List<Phone> phones = new ArrayList<Phone>();
    @ManyToMany
    private List<Printer> printers = new ArrayList<Printer>();

    public User() {
    }


    public List<Computer> getComputers() {
        return computers;
    }
    public User(
            String name,
            String surname,
            String bitrix,
            List<Computer> computers,
            Department department,
            List<Phone> phones,
            List<Printer> printers
    ){
        this.name = name;
        this.surname = surname;
        this.bitrix = bitrix;
        this.computers = computers;
        this.department = department;
        this.phones = phones;
        this.printers = printers;
    }
}
