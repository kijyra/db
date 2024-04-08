package ru.dallari.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String IP, model;
    private Integer number;
    @ManyToOne
    private Location location;
    @ManyToMany(mappedBy = "phones")
    private List<User> userList;
    @NonNull
    transient private boolean isOnline;


    public Phone() {
    }

    public Phone(String IP, String model, Integer number, Location location){
        this.IP = IP;
        this.model = model;
        this.number = number;
        this.location = location;
    }

    public boolean isOnline() {
        try {
            InetAddress inet = InetAddress.getByName(this.IP);
            isOnline = inet.isReachable(5000);
        } catch (UnknownHostException e) {
            System.out.println("Host is unknown");
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isOnline;
    }

}
