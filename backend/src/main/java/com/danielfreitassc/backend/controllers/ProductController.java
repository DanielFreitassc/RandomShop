package com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.ProductDTO;
import com.danielfreitassc.backend.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDTO create(@RequestBody @Valid ProductDTO productDTO) {
        return productService.create(productDTO);
    }
    
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("{id}")
    public ProductDTO delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
