package br.com.booksy.Booksy.controller;
import br.com.booksy.Booksy.domain.dto.ReviewRequestDTO;
import br.com.booksy.Booksy.domain.model.Review;
import br.com.booksy.Booksy.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    public List<Review> findAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewRequestDTO findReviewById(UUID id){
        return reviewService.findReviewById(id);
    }

    @PostMapping()
    public ReviewRequestDTO save(@RequestBody ReviewRequestDTO reviewRequestDTO){
        return reviewService.save(reviewRequestDTO);
    }

    @PutMapping("/{id}")
    public ReviewRequestDTO update(@PathVariable UUID id, @RequestBody ReviewRequestDTO reviewRequestDTO){
        reviewRequestDTO.setId(id);
        return reviewService.update(reviewRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        reviewService.deleteById(id);
    }
}
