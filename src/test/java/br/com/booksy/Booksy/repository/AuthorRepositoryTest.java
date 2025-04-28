package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.util.AuthorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Author Repository")
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Save persists author when successful")
    void save_PersistsAuthor_WhenSuccessful() {
        Author author = AuthorCreator.createAuthor();
        Author savedAuthor = authorRepository.save(author);

        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getId()).isNotNull();
        Assertions.assertThat(savedAuthor.getFirstName()).isEqualTo(author.getFirstName());
        Assertions.assertThat(savedAuthor.getLastName()).isEqualTo(author.getLastName());
        Assertions.assertThat(savedAuthor.getOriginCountry()).isEqualTo(author.getOriginCountry());
        Assertions.assertThat(savedAuthor.getBirthYear()).isEqualTo(author.getBirthYear());
    }

    @Test
    @DisplayName("Save updates author when successful")
    void save_UpdatesAuthor_WhenSuccessful() {
        Author author = AuthorCreator.createAuthor();
        Author savedAuthor = authorRepository.save(author);
        savedAuthor.setFirstName("Steven");
        savedAuthor.setLastName("Hawking");
        savedAuthor.setOriginCountry("Brazil");
        savedAuthor.setBirthYear(1945);
        Author updatedAuthor = authorRepository.save(savedAuthor);

        Assertions.assertThat(updatedAuthor).isNotNull();
        Assertions.assertThat(updatedAuthor.getId()).isNotNull().isEqualTo(savedAuthor.getId());
        Assertions.assertThat(updatedAuthor.getFirstName()).isEqualTo(savedAuthor.getFirstName());
        Assertions.assertThat(updatedAuthor.getLastName()).isEqualTo(savedAuthor.getLastName());
        Assertions.assertThat(updatedAuthor.getOriginCountry()).isEqualTo(savedAuthor.getOriginCountry());
        Assertions.assertThat(updatedAuthor.getBirthYear()).isEqualTo(savedAuthor.getBirthYear());
    }

    @Test
    @DisplayName("Delete removes author when successful")
    void delete_RemovesAuthor_WhenSuccessful() {
        Author author = AuthorCreator.createAuthor();
        Author savedAuthor = authorRepository.save(author);
        authorRepository.delete(author);

        Assertions.assertThat(authorRepository.findById(savedAuthor.getId())).isEmpty();
    }
}