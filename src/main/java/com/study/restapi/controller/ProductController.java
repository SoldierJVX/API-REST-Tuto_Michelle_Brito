package com.study.restapi.controller;

import com.study.restapi.model.Product;
import com.study.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product){
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long id){
        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productRepository.delete(product.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") long id,
                                                 @RequestBody @Valid Product newProduct){
        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            newProduct.setId(product.get().getId());
            return new ResponseEntity<Product>(productRepository.save(newProduct), HttpStatus.OK);
        }
    }

}
