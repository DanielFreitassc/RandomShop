package com.daniel.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.loja.models.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    
}
