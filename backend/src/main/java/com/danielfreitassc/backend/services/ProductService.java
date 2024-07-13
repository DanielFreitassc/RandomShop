package com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.ProductDTO;
import com.danielfreitassc.backend.mappers.ProductMapper;
import com.danielfreitassc.backend.models.ProductEntity;
import com.danielfreitassc.backend.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductDTO create(ProductDTO productDTO) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    public ProductDTO getById(Long id) {
        Optional<ProductEntity> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado");
        return productMapper.toDto(optProduct.get());
    }

    public ProductDTO update(Long id, ProductDTO productDTO) {
        Optional<ProductEntity> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado");
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        productEntity.setId(id);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    public ProductDTO delete(Long id) {
        Optional<ProductEntity> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado");
        productRepository.delete(optProduct.get());
        return productMapper.toDto(optProduct.get());
    }
    
}
