package br.com.booksy.Booksy.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookDTO {
    private UUID id;
    private String title;
    private String isbn;
    private Integer pagesNumber;
    private LocalDateTime releaseDate;
}
