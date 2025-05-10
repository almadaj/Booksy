package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    @NotBlank(message = "name cannot be empty")
    @NotNull(message = "name cannot be empty")
    private String name;
}
