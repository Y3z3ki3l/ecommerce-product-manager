package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    // Encuentra productos por categoría
  //  List<Product> findByCategory(String category);

    //Encuentra productos por Id
   Optional<Product> findById(String id);
 }

