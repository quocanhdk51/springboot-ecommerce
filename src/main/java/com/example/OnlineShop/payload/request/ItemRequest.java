package com.example.OnlineShop.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemRequest {
    @NotNull
    private long productId;

    @NotNull
    private int quantity;
}
