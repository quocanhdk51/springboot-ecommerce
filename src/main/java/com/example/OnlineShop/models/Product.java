package com.example.OnlineShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table( name = "products" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Brand brand;

    @Column
    private String description;

    @Column
    private String content;

    @Column
    private double price;

    @Column
    private int quantity;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Item> items;

    private int count = 1;

    public Product(String title, String image, Brand brand, String description, String content, double price, int quantity) {
        this.title = title;
        this.image = image;
        this.brand = brand;
        this.description = description;
        this.content = content;
        this.price = price;
        this.quantity = quantity;
    }
}
