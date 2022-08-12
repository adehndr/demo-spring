package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.ResponseData;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.models.entities.Supplier;
import com.example.demo.services.SupplierService;

import org.modelmapper.ModelMapper;
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

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<Supplier>();
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<String>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }
            responseData.setMessages(listError);
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
/*         Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setEmail(supplierDTO.getEmail()); */
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findById(@PathVariable("id") Long id) {
        return supplierService.findById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<Supplier>();
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<String>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }
            responseData.setMessages(listError);
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
/*         Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setEmail(supplierDTO.getEmail()); */
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id){
        supplierService.deleteProduct(id);
    }
}
