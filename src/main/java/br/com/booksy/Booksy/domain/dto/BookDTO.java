package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class BookDTO {
    @NotBlank(message = "title cannot be empty")
    @NotNull(message = "title cannot be empty")
    private String title;

    @NotBlank(message = "isbn cannot be empty")
    @Pattern(
        regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|" +
                "(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|" +
                "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?" +
                "[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
        message = "invalid ISBN"
    )
    private String isbn;

    private String uploadId;

    private String viewLink;

    @NotNull(message = "pagesNumber cannot be empty")
    private Integer pagesNumber;

    private Integer releaseDate;

    @NotNull(message = "authorId cannot be empty")
    private UUID authorId;

    private String cover;

    private Set<UUID> categoryIds;
}
