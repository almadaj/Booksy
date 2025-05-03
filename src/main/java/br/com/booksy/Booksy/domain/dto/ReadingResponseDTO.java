package br.com.booksy.Booksy.domain.dto;

import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.domain.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ReadingResponseDTO {
    @NotNull()
    private UUID userId;

    @NotNull()
    private UUID bookId;

    @NotNull()
    private int currentPage;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private User user;
    private Author author;
}
