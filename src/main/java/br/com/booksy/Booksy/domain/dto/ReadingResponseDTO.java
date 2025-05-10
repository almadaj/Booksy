package br.com.booksy.Booksy.domain.dto;

import br.com.booksy.Booksy.domain.model.Book;
import br.com.booksy.Booksy.domain.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReadingResponseDTO {
    private UUID id;
    private int currentPage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookResponseLowDTO book;
}
