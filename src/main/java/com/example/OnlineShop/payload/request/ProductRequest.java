package com.example.OnlineShop.payload.request;

import com.example.OnlineShop.models.Brand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String image;

    @NotNull
    private Brand brand;

    private String description;

    private String content;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

}
