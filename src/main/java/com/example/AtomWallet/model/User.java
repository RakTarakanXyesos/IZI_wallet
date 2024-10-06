package com.example.AtomWallet.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

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

    @Column(name = "addres")
    private String address;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Wallet> wallets;

    public Set<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(Set<Wallet> wallets) {
        this.wallets = wallets;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
