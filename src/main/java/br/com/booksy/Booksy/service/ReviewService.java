package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.ReviewRequestDTO;
import br.com.booksy.Booksy.domain.mapper.ReviewMapper;
import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.domain.model.Review;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public ReviewRequestDTO findReviewById(UUID id){
        return reviewRepository.findById(id)
                .map(review -> reviewMapper.reviewToReviewDTO(review))
                .orElseThrow(
                        () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Review not found"));
    }

    public ReviewRequestDTO save(ReviewRequestDTO reviewRequestDTO){
        try{
            var newReading = reviewRepository.save(reviewMapper.reviewDTOtoReview(reviewRequestDTO));
            return reviewMapper.reviewToReviewDTO(newReading);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while saving review");
        }
    }

    public ReviewRequestDTO update(ReviewRequestDTO reviewRequestDTO) {
        try {
            var updatedReview = reviewRepository.save(reviewMapper.reviewDTOtoReview(reviewRequestDTO));
            return reviewMapper.reviewToReviewDTO(updatedReview);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating review");
        }
    }

    public void deleteById(UUID id) {
        reviewRepository.deleteById(id);
    }
}
