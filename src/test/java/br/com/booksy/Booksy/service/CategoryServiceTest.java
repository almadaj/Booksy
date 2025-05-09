//package br.com.booksy.Booksy.service;
//
//import br.com.booksy.Booksy.domain.model.Category;
//import br.com.booksy.Booksy.exception.CommonException;
//import br.com.booksy.Booksy.repository.CategoryRepository;
//import br.com.booksy.Booksy.util.CategoryCreator;
//import br.com.booksy.Booksy.util.CategoryDTOCreator;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@ExtendWith(SpringExtension.class)
//@DisplayName("Tests for Category Service")
//class CategoryServiceTest {
//    @InjectMocks
//    private CategoryService categoryService;
//    @Mock
//    private CategoryRepository categoryRepositoryMock;
//
//    @BeforeEach
//    void setUp() {
//        // categoryRepository.findAll
//        BDDMockito.when(categoryRepositoryMock.findAll())
//                .thenReturn(List.of(CategoryCreator.createValidCategory()));
//        // categoryRepository.findById
//        BDDMockito.when(categoryRepositoryMock.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.of(CategoryCreator.createValidCategory()));
//        // categoryRepository.save
//        BDDMockito.when(categoryRepositoryMock.save(ArgumentMatchers.any(Category.class)))
//                .thenReturn(CategoryCreator.createValidCategory());
//        // categoryRepository.delete
//        BDDMockito.doNothing().when(categoryRepositoryMock)
//                .delete(ArgumentMatchers.any(Category.class));
//    }
//
//    @Test
//    @DisplayName("findAll returns list of categories when successful")
//    void findAll_ReturnsListOfCategories_WhenSuccessful() {
//        Category expectedCategory = CategoryCreator.createValidCategory();
//        List<Category> categoryList = categoryService.findAll();
//
//        Assertions.assertThat(categoryList)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//        Assertions.assertThat(categoryList.getFirst()).isEqualTo(expectedCategory);
//    }
//
//    @Test
//    @DisplayName("findById returns category when successful")
//    void findById_ReturnsCategory_WhenSuccessful() {
//        Category expectedCategory = CategoryCreator.createValidCategory();
//        Category category = categoryService.findById(expectedCategory.getId());
//
//        Assertions.assertThat(category).isNotNull();
//        Assertions.assertThat(category.getId())
//                .isNotNull()
//                .isEqualTo(expectedCategory.getId());
//        Assertions.assertThat(category).isEqualTo(expectedCategory);
//    }
//
//    @Test
//    @DisplayName("findById throws CommonException when category is not found")
//    void findById_ThrowsCommonException_WhenCategoryNotFound() {
//        BDDMockito.when(categoryRepositoryMock.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.empty());
//
//        Assertions.assertThatExceptionOfType(CommonException.class)
//                .isThrownBy(() -> categoryService.findById(UUID.randomUUID()));
//    }
//
//    @Test
//    @DisplayName("save returns category when successful")
//    void save_ReturnsCategory_WhenSuccessful() {
//        Category expectedCategory = CategoryCreator.createValidCategory();
//        Category category = categoryService.save(CategoryDTOCreator.createCategoryDTO());
//
//        Assertions.assertThat(category)
//                .isNotNull()
//                .isEqualTo(expectedCategory);
//    }
//
//    @Test
//    @DisplayName("update returns updated category when successful")
//    void update_ReturnsUpdatedCategory_WhenSuccessful() {
//        BDDMockito.when(categoryRepositoryMock.save(ArgumentMatchers.any(Category.class)))
//                .thenReturn(CategoryCreator.createValidUpdatedCategory());
//
//        Category expectedCategory = CategoryCreator.createValidUpdatedCategory();
//        Category updatedCategory = categoryService.update(expectedCategory.getId(), CategoryDTOCreator.createUpdatedCategoryDTO());
//
//        Assertions.assertThat(updatedCategory).isNotNull().isEqualTo(expectedCategory);
//    }
//
//    @Test
//    @DisplayName("delete removes category when successful")
//    void delete_RemovesCategory_WhenSuccessful() {
//        Assertions.assertThatCode(() -> categoryService.delete(UUID.randomUUID()))
//                .doesNotThrowAnyException();
//    }
//}