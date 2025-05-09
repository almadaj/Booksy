//package br.com.booksy.Booksy.service;
//
//import br.com.booksy.Booksy.domain.model.Author;
//import br.com.booksy.Booksy.exception.CommonException;
//import br.com.booksy.Booksy.repository.AuthorRepository;
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
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@ExtendWith(SpringExtension.class)
//@DisplayName("Tests for Author Service")
//class AuthorServiceTest {
//    @InjectMocks
//    private AuthorService authorService;
//    @Mock
//    private AuthorRepository authorRepositoryMock;
//
//    @BeforeEach
//    void setUp() {
//        // authorRepository.findAll
//        BDDMockito.when(authorRepositoryMock.findAll())
//                .thenReturn(List.of(AuthorCreator.createValidAuthor()));
//        // authorRepository.findById
//        BDDMockito.when(authorRepositoryMock.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.of(AuthorCreator.createValidAuthor()));
//        // authorRepository.save
//        BDDMockito.when(authorRepositoryMock.save(ArgumentMatchers.any(Author.class)))
//                .thenReturn(AuthorCreator.createValidAuthor());
//        // authorRepository.delete
//        BDDMockito.doNothing().when(authorRepositoryMock)
//                .delete(ArgumentMatchers.any(Author.class));
//    }
//
//    @Test
//    @DisplayName("findAll returns list of authors when successful")
//    void findAll_ReturnsListOfAuthors_WhenSuccessful() {
//        Author expectedAuthor = AuthorCreator.createValidAuthor();
//        List<Author> authorList = authorService.findAll();
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
//        Author author = authorService.findById(expectedAuthor.getId());
//
//        Assertions.assertThat(author).isNotNull();
//        Assertions.assertThat(author.getId())
//                .isNotNull()
//                .isEqualTo(expectedAuthor.getId());
//        Assertions.assertThat(author).isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("findById throws CommonException when author is not found")
//    void findById_ThrowsCommonException_WhenAuthorNotFound() {
//        BDDMockito.when(authorRepositoryMock.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.empty());
//
//        Assertions.assertThatExceptionOfType(CommonException.class)
//                .isThrownBy(() -> authorService.findById(UUID.randomUUID()));
//    }
//
//    @Test
//    @DisplayName("save returns author when successful")
//    void save_ReturnsAuthor_WhenSuccessful() {
//        Author expectedAuthor = AuthorCreator.createValidAuthor();
//        Author author = authorService.save(AuthorDTOCreator.createAuthorDTO());
//
//        Assertions.assertThat(author)
//                .isNotNull()
//                .isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("update returns updated author when successful")
//    void update_ReturnsUpdatedAuthor_WhenSuccessful() {
//        BDDMockito.when(authorRepositoryMock.save(ArgumentMatchers.any(Author.class)))
//                .thenReturn(AuthorCreator.createValidUpdatedAuthor());
//
//        Author expectedAuthor = AuthorCreator.createValidUpdatedAuthor();
//        Author updatedAuthor = authorService.update(expectedAuthor.getId(), AuthorDTOCreator.createUpdatedAuthorDTO());
//
//        Assertions.assertThat(updatedAuthor).isNotNull().isEqualTo(expectedAuthor);
//    }
//
//    @Test
//    @DisplayName("delete removes author when successful")
//    void delete_RemovesAuthor_WhenSuccessful() {
//        Assertions.assertThatCode(() -> authorService.delete(UUID.randomUUID()))
//                .doesNotThrowAnyException();
//    }
//}