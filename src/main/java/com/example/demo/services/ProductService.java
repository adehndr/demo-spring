package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.entities.Product;
import com.example.demo.models.entities.Supplier;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo;

    public Product create(Product product){
        return productRepo.save(product);
    }

    public Product findById(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> findByNameContains(String name){
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findById(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID : "+ productId + " not found");
        }
        Set<Supplier> tempSetSupplierFromProd = product.getSuppliers();
        tempSetSupplierFromProd.add(supplier);
        product.setSuppliers(tempSetSupplierFromProd);
        create(product);
    }
}
