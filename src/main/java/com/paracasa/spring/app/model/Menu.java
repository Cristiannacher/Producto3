package com.paracasa.spring.app.model;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "menusAssociated", fetch = FetchType.LAZY)
    private Set<Product> productsAssociated = new LinkedHashSet<>();

    public Menu(String name, double price) {
        this.setName(name);
        this.setPrice(price);
    }

    public Menu() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Product> getProductsAssociated() {
        return productsAssociated;
    }

    public void setProductsAssociated(Set<Product> productsAssociated) {
        this.productsAssociated = productsAssociated;
    }
}