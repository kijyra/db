package ru.dallari.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


@Entity
@AllArgsConstructor
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String DNSname, IP, office;
    private Boolean Domain;
    private Boolean ThinClient;
    @ManyToOne
    private Location location;
    @ManyToMany(mappedBy = "computers")
    private List<User> userList;
    @NonNull
    transient private boolean isOnline;


    public Computer() {
    }

    public Computer(String DNSname, Boolean domain, Boolean thinClient,String IP, String office, Location location){
        this.DNSname = DNSname;
        this.Domain = domain;
        this.ThinClient = thinClient;
        this.IP = IP;
        this.office = office;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getDNSname() {
        return DNSname;
    }

    public String getIP() {
        return IP;
    }

    public String getOffice() {
        return office;
    }

    public Boolean getDomain() {
        return Domain;
    }
    public Boolean getThinClient() {
        return ThinClient;
    }

    public Location getLocation() {
        return location;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDNSname(String DNSname) {
        this.DNSname = DNSname;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setDomain(Boolean domain) {
        this.Domain = domain;
    }

    public void setThinClient(Boolean thinClient) {
        this.ThinClient = thinClient;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean isOnline() {
        try {
            InetAddress inet = InetAddress.getByName(this.IP);
            isOnline = inet.isReachable(1000);
            System.out.println("host.isReachable(1000) = " + isOnline);
        } catch (UnknownHostException e) {
            System.out.println("Host is unknown");
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("final = " + isOnline);
        return isOnline;
    }
}
