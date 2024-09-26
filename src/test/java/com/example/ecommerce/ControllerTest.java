package com.example.ecommerce;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    // Test cuando el producto existe
    @Test
    public void testGetProductById_Found() throws Exception {
        // Creamos un producto de ejemplo
        Product product = new Product(1L, "Producto A", 100.0);

        // Configuramos el servicio para devolver este producto
        Mockito.when(productService.getProductById("1")).thenReturn(Optional.of(product));

        // Hacemos la petición GET y verificamos la respuesta
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Producto A"))
                .andExpect(jsonPath("$.quantity").value(100.0));
        ;
    }

    // Test cuando el producto no existe
    @Test
    public void testGetProductById_NotFound() throws Exception {
        // Configuramos el servicio para devolver un Optional vacío
        Mockito.when(productService.getProductById("2")).thenReturn(Optional.empty());

        // Hacemos la petición GET y verificamos que devuelve 404
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/2"))
                .andExpect(status().isNotFound());
    }
}
