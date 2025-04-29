package br.com.booksy.Booksy.util;

import br.com.booksy.Booksy.domain.dto.AuthorDTO;

public class AuthorDTOCreator {
    public static AuthorDTO createAuthorDTO() {
        return AuthorDTO.builder()
                .firstName(AuthorCreator.createAuthor().getFirstName())
                .lastName(AuthorCreator.createAuthor().getLastName())
                .originCountry(AuthorCreator.createAuthor().getOriginCountry())
                .birthYear(AuthorCreator.createAuthor().getBirthYear())
                .build();
    }

    public static AuthorDTO createUpdatedAuthorDTO() {
        return AuthorDTO.builder()
                .firstName(AuthorCreator.createValidUpdatedAuthor().getFirstName())
                .lastName(AuthorCreator.createValidUpdatedAuthor().getLastName())
                .originCountry(AuthorCreator.createValidUpdatedAuthor().getOriginCountry())
                .birthYear(AuthorCreator.createValidUpdatedAuthor().getBirthYear())
                .build();
    }
}
