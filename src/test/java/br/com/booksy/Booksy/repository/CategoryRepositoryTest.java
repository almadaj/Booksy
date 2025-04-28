package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.util.CategoryCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Category Repository")
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Save persists category when successful")
    void save_PersistsCategory_WhenSuccessful() {
        Category category = CategoryCreator.createCategory();
        Category savedCategory = categoryRepository.save(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isNotNull();
        Assertions.assertThat(savedCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    @DisplayName("Save updates category when successful")
    void save_UpdatesCategory_WhenSuccessful() {
        Category category = CategoryCreator.createCategory();
        Category savedCategory = categoryRepository.save(category);
        savedCategory.setName("Horror");
        Category updatedCategory = categoryRepository.save(savedCategory);

        Assertions.assertThat(updatedCategory).isNotNull();
        Assertions.assertThat(updatedCategory.getId()).isNotNull().isEqualTo(savedCategory.getId());
        Assertions.assertThat(updatedCategory.getName()).isEqualTo(savedCategory.getName());
    }

    @Test
    @DisplayName("Delete removes category when successful")
    void delete_RemovesCategory_WhenSuccessful() {
        Category category = CategoryCreator.createCategory();
        Category savedCategory = categoryRepository.save(category);
        categoryRepository.delete(category);

        Assertions.assertThat(categoryRepository.findById(savedCategory.getId())).isEmpty();
    }
}