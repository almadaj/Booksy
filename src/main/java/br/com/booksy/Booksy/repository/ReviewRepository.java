package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
