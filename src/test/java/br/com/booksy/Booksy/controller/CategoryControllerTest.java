package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;
import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.service.AuthorService;
import br.com.booksy.Booksy.service.CategoryService;
import br.com.booksy.Booksy.util.CategoryCreator;
import br.com.booksy.Booksy.util.CategoryDTOCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Category Controller")
class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryServiceMock;

    @BeforeEach
    void setUp() {
        // categoryService.findAll
        BDDMockito.when(categoryServiceMock.findAll())
                .thenReturn(List.of(CategoryCreator.createValidCategory()));
        // categoryService.findById
        BDDMockito.when(categoryServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(CategoryCreator.createValidCategory());
        // categoryService.save
        BDDMockito.when(categoryServiceMock.save(ArgumentMatchers.any(CategoryDTO.class)))
                .thenReturn(CategoryCreator.createValidCategory());
        // categoryService.update
        BDDMockito.when(categoryServiceMock.update(ArgumentMatchers.any(), ArgumentMatchers.any(CategoryDTO.class)))
                .thenReturn(CategoryCreator.createValidUpdatedCategory());
        // categoryService.delete
        BDDMockito.doNothing().when(categoryServiceMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("findAll returns list of categories when successful")
    void findAll_ReturnsListOfCategories_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createValidCategory();
        List<Category> categoryList = categoryController.findAll().getBody();

        Assertions.assertThat(categoryList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(categoryList.getFirst()).isEqualTo(expectedCategory);
    }

    @Test
    @DisplayName("findById returns category when successful")
    void findById_ReturnsCategory_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createValidCategory();
        Category category = categoryController.findById(expectedCategory.getId()).getBody();

        Assertions.assertThat(category)
                .isNotNull()
                .isEqualTo(expectedCategory);
    }

    @Test
    @DisplayName("save returns category when successful")
    void save_ReturnsCategory_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createValidCategory();
        Category category = categoryController.save(CategoryDTOCreator.createCategoryDTO()).getBody();

        Assertions.assertThat(category)
                .isNotNull()
                .isEqualTo(expectedCategory);
    }

    @Test
    @DisplayName("update returns updated category when successful")
    void update_ReturnsUpdatedCategory_WhenSuccessful() {
        Category expectedCategory = CategoryCreator.createValidUpdatedCategory();
        Category updatedCategory = categoryController.update(expectedCategory.getId(), CategoryDTOCreator.createUpdatedCategoryDTO()).getBody();

        Assertions.assertThat(updatedCategory)
                .isNotNull()
                .isEqualTo(expectedCategory);
    }

    @Test
    @DisplayName("delete removes author when successful")
    void delete_RemovesAuthor_WhenSuccessful() {
        Assertions.assertThatCode(() -> categoryController.delete(UUID.randomUUID())).doesNotThrowAnyException();
        ResponseEntity<Void> entity = categoryController.delete(UUID.randomUUID());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}