package com.danielfreitassc.backend.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.ProductRequestDTO;
import com.danielfreitassc.backend.dtos.ProductResponseDto;
import com.danielfreitassc.backend.mappers.ProductMapper;
import com.danielfreitassc.backend.models.ProductEntity;
import com.danielfreitassc.backend.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductResponseDto create(ProductRequestDTO productRequestDTO) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productRequestDTO)));
    }

    public Page<ProductResponseDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    public ProductResponseDto getById(Long id) {
        ProductEntity productEntity = checkId(id);
        return productMapper.toDto(productEntity);
    }

    public ProductResponseDto update(Long id, ProductRequestDTO productRequestDTO) {
        checkId(id);
        ProductEntity productEntity = productMapper.toEntity(productRequestDTO);
        productEntity.setId(id);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    public ProductResponseDto delete(Long id) {
        ProductEntity productEntity = checkId(id);
        productRepository.delete(productEntity);
        return productMapper.toDto(productEntity);
    }
    
    public ProductEntity checkId(Long id) {
        Optional<ProductEntity> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado");
        return optProduct.get();
    }
}
