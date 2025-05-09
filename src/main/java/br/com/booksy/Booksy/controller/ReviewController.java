package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReviewRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReviewResponseDTO;
import br.com.booksy.Booksy.domain.model.Review;
import br.com.booksy.Booksy.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    public ResponseEntity<List<ReviewResponseDTO>> findAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ReviewResponseDTO> save(@RequestBody @Valid ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.save(reviewRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.ok(reviewService.update(id, reviewRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
