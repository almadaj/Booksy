package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class ReviewRequestDTO {
    @NotNull()
    private UUID userId;

    @NotNull()
    private UUID bookId;

    @NotNull()
    private int rating;

    @NotNull()
    private String title;

    @NotNull()
    private String textPost;

}
