package com.example.AtomWallet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "name")
    private String name;

    @Column(unique = true, name = "email")
    private String email;

    @Column(unique = true, name = "contact_number")
    private String conatact_number;

    @Column(name = "addres")
    private String address;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConatact_number() {
        return conatact_number;
    }

    public void setConatact_number(String conatact_number) {
        this.conatact_number = conatact_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
