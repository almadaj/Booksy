package br.com.booksy.Booksy.util;

import br.com.booksy.Booksy.domain.model.Author;

import java.util.UUID;

public class AuthorCreator {
    public static Author createAuthor() {
        return Author.builder()
                .firstName("Stephen")
                .lastName("King")
                .originCountry("United States")
                .birthYear(1947)
                .build();
    }

    public static Author createValidAuthor() {
        return Author.builder()
                .id(UUID.fromString("5b29dc77-1fa6-4507-9bf6-5293e12ee155"))
                .firstName("Stephen")
                .lastName("King")
                .originCountry("United States")
                .birthYear(1947)
                .build();
    }

    public static Author createValidUpdatedAuthor() {
        return Author.builder()
                .id(UUID.fromString("5b29dc77-1fa6-4507-9bf6-5293e12ee155"))
                .firstName("Stephen")
                .lastName("King")
                .originCountry("USA")
                .birthYear(1947)
                .build();
    }
}
