package com.example.ecommerce.model;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //private String category;
    private String name;  // Cambiado a minúsculas para seguir las convenciones de Java
    private double quantity;

    // Constructor personalizado (si es necesario)
    public Product(Long id, String name, double quantity) {
        this.Id= id;
        this.name = name;
        this.quantity = quantity;
    }
}
