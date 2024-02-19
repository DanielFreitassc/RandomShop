package com.daniel.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daniel.loja.controllers.ProductController;
import com.daniel.loja.dtos.ProductRecordDTO;
import com.daniel.loja.models.ProductEntity;
import com.daniel.loja.repositories.ProductRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ProductEntity> saveProduct(ProductRecordDTO productRecordDTO) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productRecordDTO, productEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productEntity));
    }

    public ResponseEntity<List<ProductEntity>> getAllProduct() {
        List<ProductEntity> productList = productRepository.findAll();
        if(!productList.isEmpty()) {
           for(ProductEntity productEntity : productList){
           Long id = productEntity.getId();
           productEntity.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());}
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    public ResponseEntity<Object> getOneProduct(Long id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if(productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto com este id encontrado");
        }
        productOpt.get().add(linkTo(methodOn(ProductController.class).getAllProduct()).withRel("Lista de produtos"));
        return ResponseEntity.status(HttpStatus.OK).body(productOpt);
    }

    public ResponseEntity<Object> updateProduct(Long id, ProductRecordDTO productRecordDTO) {
        Optional<ProductEntity> producOpt = productRepository.findById(id);
        if(producOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto com este id encontrado");
        }
        var productEntity = producOpt.get();
        BeanUtils.copyProperties(productRecordDTO, productEntity);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productEntity));
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if(productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto com este id encontrado");
        }
        productRepository.delete(productOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso");
    }
    
}
