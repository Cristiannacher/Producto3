package com.paracasa.spring.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "menu_products",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName =
                            "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "menu_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Menu> menusAssociated = new HashSet<>();

    public Product(String name, double price) {
        this.setName(name);
        this.setPrice(price);
    }

    public Product() {
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

    public Set<Menu> getMenusAssociated() {
        return menusAssociated;
    }

    public void setMenusAssociated(Set<Menu> menusAssociated) {
        this.menusAssociated = menusAssociated;
    }
}