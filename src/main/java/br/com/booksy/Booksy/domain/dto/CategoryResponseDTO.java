package br.com.booksy.Booksy.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryResponseDTO {
    private UUID id;
    private String name;
}
