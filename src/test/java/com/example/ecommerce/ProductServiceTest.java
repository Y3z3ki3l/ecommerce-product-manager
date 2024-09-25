package com.example.ecommerce;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertTrue;

// Ejemplo de prueba unitaria para ProductService
@ExtendWith(MockitoExtension.class)

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;



    @Test
    public void testGetProductById_Found() {
        Product product = new Product(1L,"Producto A", 100.0);
        Mockito.when(productRepository.findById("1")).thenReturn(Optional.of(product));  // Si no se usa, no falla


        Optional<Product> result = productService.getProductById("1");
        assertTrue(result.isPresent());
        Assertions.assertEquals("Producto A", result.get().getName());
    }

    @Test
    public void testGetProductById_NotFound() {
        Mockito.when(productRepository.findById("2")).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById("2");
        Assertions.assertFalse(result.isPresent());
    }
}