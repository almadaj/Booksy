package br.com.booksy.Booksy.controller;
import br.com.booksy.Booksy.domain.dto.ReviewDTO;
import br.com.booksy.Booksy.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ReviewDTO findReviewById(UUID id){
        return reviewService.findReviewById(id);
    }

    @PostMapping()
    public ReviewDTO save(@RequestBody ReviewDTO reviewDTO){
        return reviewService.save(reviewDTO);
    }

    @PutMapping()
    public ReviewDTO update(@RequestBody ReviewDTO reviewDTO){
        return reviewService.update(reviewDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        reviewService.deleteById(id);
    }
}
