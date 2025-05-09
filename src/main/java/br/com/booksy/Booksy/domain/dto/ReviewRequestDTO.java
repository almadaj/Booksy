package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class ReviewRequestDTO {
    @NotNull(message = "userId cannot be empty")
    private UUID userId;

    @NotNull(message = "bookId cannot be empty")
    private UUID bookId;

    @NotNull(message = "rating cannot be empty")
    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank(message = "title cannot be empty")
    @NotNull(message = "title cannot be empty")
    private String title;

    @NotNull(message = "textPost cannot be empty")
    private String textPost;
}
