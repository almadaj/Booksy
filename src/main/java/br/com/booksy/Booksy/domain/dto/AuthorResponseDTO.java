package br.com.booksy.Booksy.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthorResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String originCountry;
    private String birthYear;
}
