package br.com.booksy.Booksy.repository;

import br.com.booksy.Booksy.domain.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReadingRepository extends JpaRepository<Reading, UUID> {
    List<Reading> findByUserId(UUID userId);
}
