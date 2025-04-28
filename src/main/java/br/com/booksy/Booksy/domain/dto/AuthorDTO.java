package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO {
    @NotEmpty(message = "firstName cannot be empty")
    @NotNull(message = "firstName cannot be empty")
    private String firstName;

    @NotEmpty(message = "lastName cannot be empty")
    @NotNull(message = "lastName cannot be empty")
    private String lastName;

    @NotEmpty(message = "originCountry cannot be empty")
    @NotNull(message = "originCountry cannot be empty")
    private String originCountry;

    @NotNull(message = "birthYear cannot be empty")
    private Integer birthYear;
}
