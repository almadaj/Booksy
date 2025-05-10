package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO {
    @NotBlank(message = "firstName cannot be empty")
    @NotNull(message = "firstName cannot be empty")
    private String firstName;

    @NotBlank(message = "lastName cannot be empty")
    @NotNull(message = "lastName cannot be empty")
    private String lastName;

    @NotBlank(message = "originCountry cannot be empty")
    @NotNull(message = "originCountry cannot be empty")
    private String originCountry;

    @NotNull(message = "birthYear cannot be empty")
    private Integer birthYear;
}
