package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
