package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entities.Category;
import com.example.demo.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Category findById(Long id){
        Optional<Category> product = categoryRepository.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void deleteProduct(Long id){
        categoryRepository.deleteById(id);
    }

    public List<Category> findByNameContains(String name){
        return categoryRepository.findByNameContains(name);
    }
    
}
