package com.example.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;

import java.util.Optional;



@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);  // Devuelve el producto encontrado, si existe
    }



}
