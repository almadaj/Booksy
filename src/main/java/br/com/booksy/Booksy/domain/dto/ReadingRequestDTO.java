package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class ReadingRequestDTO {
    @NotNull(message = "userId cannot be empty")
    private UUID userId;

    @NotNull(message = "bookId cannot be empty")
    private UUID bookId;

    @NotNull(message = "currentPage cannot be empty")
    private int currentPage;
}
