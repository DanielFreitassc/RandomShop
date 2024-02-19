package com.daniel.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.loja.dtos.ProductRecordDTO;
import com.daniel.loja.models.ProductEntity;
import com.daniel.loja.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PostMapping
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.saveProduct(productRecordDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProduct() {
        return productService.getAllProduct();
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @GetMapping("{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id")Long id) {
        return productService.getOneProduct(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")Long id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.updateProduct(id, productRecordDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders ="*")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")Long id) {
        return productService.deleteProduct(id);
    }    
}
