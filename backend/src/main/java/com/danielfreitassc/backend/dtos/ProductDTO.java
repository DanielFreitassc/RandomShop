package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
    Long id,
    @NotBlank(message = "O campo nome não pode estar vazio") String name,
    @NotBlank(message = "O camo descrição não pode estar vazio") String description,
    @NotNull(message = "O preço não pode ser nulo") Double price,
    boolean available
) {
    
}
