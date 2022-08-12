package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseData;
import com.example.demo.models.entities.Product;
import com.example.demo.models.entities.Supplier;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@RequestBody @Valid Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<Product>();
        if (errors.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError iterable_element : errors.getAllErrors()) {
                errorList.add(iterable_element.getDefaultMessage());
            }
            responseData.setMessages(errorList);
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.create(product));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
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
    public ResponseEntity<ResponseData<Product>> update(@RequestBody @Valid Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<Product>();
        if (errors.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError iterable_element : errors.getAllErrors()) {
                errorList.add(iterable_element.getDefaultMessage());
            }
            responseData.setMessages(errorList);
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.create(product));
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier, productId);
    }
}
