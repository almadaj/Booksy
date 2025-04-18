package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
