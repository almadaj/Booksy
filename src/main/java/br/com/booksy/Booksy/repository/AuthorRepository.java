package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface
AuthorRepository extends JpaRepository<Author, UUID> {}
