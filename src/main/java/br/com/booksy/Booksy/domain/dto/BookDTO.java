package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookDTO {
    private UUID id;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @Pattern(
        regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|" +
                "(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|" +
                "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?" +
                "[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
        message = "ISBN inválido"
    )
    private String isbn;
    private String uploadId;
    @NotNull
    private Integer pagesNumber;
    private Integer releaseDate;
}
