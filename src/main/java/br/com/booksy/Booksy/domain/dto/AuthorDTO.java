package br.com.booksy.Booksy.domain.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private String firstName;
    private String lastName;
    private String originCountry;
    private Integer birthYear;
}
