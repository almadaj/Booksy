package br.com.booksy.Booksy.util;

import br.com.booksy.Booksy.domain.model.Category;

import java.util.UUID;

public class CategoryCreator {
    public static Category createCategory() {
        return Category.builder()
                .name("Ficção")
                .build();
    }

    public static Category createValidCategory() {
        return Category.builder()
                .id(UUID.fromString("497da3ca-4d55-4f67-8091-2270bb6fa758"))
                .name("Ficção")
                .build();
    }

    public static Category createValidUpdatedCategory() {
        return Category.builder()
                .id(UUID.fromString("497da3ca-4d55-4f67-8091-2270bb6fa758"))
                .name("Horror")
                .build();
    }
}
