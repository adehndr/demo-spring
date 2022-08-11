package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entities.Product;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findByOne(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product){
        return productService.create(product);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
