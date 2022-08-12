package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier,Long>{
    
}
