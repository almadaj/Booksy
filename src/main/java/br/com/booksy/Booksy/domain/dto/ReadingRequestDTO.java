package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class ReadingRequestDTO {
    private UUID id;
    @NotNull()
    private UUID userId;

    @NotNull()
    private UUID bookId;

    @NotNull()
    private int currentPage;
}
