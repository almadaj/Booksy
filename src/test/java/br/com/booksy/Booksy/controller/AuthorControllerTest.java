//package br.com.booksy.Booksy.controller;
//
//import br.com.booksy.Booksy.domain.dto.AuthorDTO;
//import br.com.booksy.Booksy.domain.model.Author;
//import br.com.booksy.Booksy.service.AuthorService;
//import br.com.booksy.Booksy.util.AuthorCreator;
//import br.com.booksy.Booksy.util.AuthorDTOCreator;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.UUID;
//
//@ExtendWith(SpringExtension.class)
//@DisplayName("Tests for Author Controller")
//class AuthorControllerTest {
//    @InjectMocks
//    private AuthorController authorController;
//    @Mock
//    private AuthorService authorServiceMock;
//
//    @BeforeEach
//    void setUp() {
//        // authorService.findAll
//        BDDMockito.when(authorServiceMock.findAll())
//                .thenReturn(List.of(AuthorCreator.createValidAuthor()));
//        // authorService.findById
//        BDDMockito.when(authorServiceMock.findById(ArgumentMatchers.any()))
//                .thenReturn(AuthorCreator.createValidAuthor());
//        // authorService.save
//        BDDMockito.when(authorServiceMock.save(ArgumentMatchers.any(AuthorDTO.class)))
//                .thenReturn(AuthorCreator.createValidAuthor());
//        // authorService.update
//        BDDMockito.when(authorServiceMock.update(ArgumentMatchers.any(), ArgumentMatchers.any(AuthorDTO.class)))
//                .thenReturn(AuthorCreator.createValidUpdatedAuthor());
//        // authorService.delete
//        BDDMockito.doNothing().when(authorServiceMock).delete(ArgumentMatchers.any());
//    }
//
//    @Test
//    @DisplayName("findAll returns list of authors when successful")
//    void findAll_ReturnsListOfAuthors_WhenSuccessful() {
//        Author expectedAuthor = AuthorCreator.createValidAuthor();
//        List<Author> authorList = authorController.findAll().getBody();
//
//        Assertions.assertThat(authorList)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//        Assertions.assertThat(authorList.getFirst()).isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("findById returns author when successful")
//    void findById_ReturnsAuthor_WhenSuccessful() {
//        Author expectedAuthor = AuthorCreator.createValidAuthor();
//        Author author = authorController.findById(expectedAuthor.getId()).getBody();
//
//        Assertions.assertThat(author)
//                .isNotNull()
//                .isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("save returns author when successful")
//    void save_ReturnsAuthor_WhenSuccessful() {
//        Author expectedAuthor = AuthorCreator.createValidAuthor();
//        Author author = authorController.save(AuthorDTOCreator.createAuthorDTO()).getBody();
//
//        Assertions.assertThat(author)
//                .isNotNull()
//                .isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("update returns updated author when successful")
//    void update_ReturnsUpdatedAuthor_WhenSuccessful() {
//        Author expectedAuthor = AuthorCreator.createValidUpdatedAuthor();
//        Author updatedAuthor = authorController.update(expectedAuthor.getId(), AuthorDTOCreator.createUpdatedAuthorDTO()).getBody();
//
//        Assertions.assertThat(updatedAuthor)
//                .isNotNull()
//                .isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("delete removes author when successful")
//    void delete_RemovesAuthor_WhenSuccessful() {
//        Assertions.assertThatCode(() -> authorController.delete(UUID.randomUUID())).doesNotThrowAnyException();
//        ResponseEntity<Void> entity = authorController.delete(UUID.randomUUID());
//
//        Assertions.assertThat(entity).isNotNull();
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }
//}