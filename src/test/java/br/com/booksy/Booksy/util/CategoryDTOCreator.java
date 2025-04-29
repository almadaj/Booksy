package br.com.booksy.Booksy.util;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;

public class CategoryDTOCreator {
    public static CategoryDTO createCategoryDTO() {
        return CategoryDTO.builder()
                .name(CategoryCreator.createCategory().getName())
                .build();
    }

    public static CategoryDTO createUpdatedCategoryDTO() {
        return CategoryDTO.builder()
                .name(CategoryCreator.createValidUpdatedCategory().getName())
                .build();
    }
}
