package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotEmpty(message = "name cannot be empty")
    private String name;
}
