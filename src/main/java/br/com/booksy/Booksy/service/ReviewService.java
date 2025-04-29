package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.ReviewDTO;
import br.com.booksy.Booksy.domain.mapper.ReviewMapper;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    public ReviewDTO findReviewById(UUID id){
        return reviewRepository.findById(id)
                .map(review -> reviewMapper.reviewToReviewDTO(review))
                .orElseThrow(
                        () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Review not found"));
    }

    public ReviewDTO save(ReviewDTO reviewDTO){
        try{
            var newReading = reviewRepository.save(reviewMapper.reviewDTOtoReview(reviewDTO));
            return reviewMapper.reviewToReviewDTO(newReading);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while saving review");
        }
    }

    public ReviewDTO update(ReviewDTO reviewDTO) {
        try {
            var updatedReview = reviewRepository.save(reviewMapper.reviewDTOtoReview(reviewDTO));
            return reviewMapper.reviewToReviewDTO(updatedReview);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating review");
        }
    }

    public void deleteById(UUID id) {
        reviewRepository.deleteById(id);
    }
}
