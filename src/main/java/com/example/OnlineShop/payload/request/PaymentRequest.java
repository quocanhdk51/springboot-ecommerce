package com.example.OnlineShop.payload.request;

import com.example.OnlineShop.models.Brand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentRequest {

    @NotNull
    private long id;

    @NotNull
    private int count;
}
