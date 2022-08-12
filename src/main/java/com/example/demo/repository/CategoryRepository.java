package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entities.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>{
    public List<Category> findByNameContains(String name);
}
