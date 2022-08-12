package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ResponseData;
import com.example.demo.models.entities.Category;
import com.example.demo.services.CategoryService;

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
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<String>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setMessages(listError);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryDTO, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            List<String> listError = new ArrayList<String>();
            for (ObjectError error : errors.getAllErrors()) {
                listError.add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setMessages(listError);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryDTO, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok().body(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.deleteProduct(id);
    }

}
