package com.danielfreitassc.backend.dtos;

public record ProductResponseDto(
    Long id,
    String name,
    String description,
    Double price,
    boolean available
) {
    
}
