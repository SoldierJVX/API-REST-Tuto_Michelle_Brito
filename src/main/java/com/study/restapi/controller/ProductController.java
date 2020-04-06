package com.study.restapi.controller;

import com.study.restapi.model.Product;
import com.study.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productsList = productRepository.findAll();

        if(productsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productsList, HttpStatus.OK);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable(value = "id") long id){
        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
    }
}
