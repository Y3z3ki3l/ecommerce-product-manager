package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {

    private Long id;

    //private String category;
    private String name;
    private double quantity;


    public ProductDTO(Long id, String name, double quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
