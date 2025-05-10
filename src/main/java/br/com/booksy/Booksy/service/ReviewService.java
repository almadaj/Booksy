package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.ReviewRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReviewResponseDTO;
import br.com.booksy.Booksy.domain.mapper.ReviewMapper;
import br.com.booksy.Booksy.domain.model.Review;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::reviewToReviewResponseDTO)
                .collect(Collectors.toList());
    }

    public ReviewResponseDTO findById(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.book.findById.notFound", "Review not found"));
        return reviewMapper.reviewToReviewResponseDTO(review);
    }

    public Review findReviewById(UUID id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.book.findById.notFound", "Review not found"));
    }

    public ReviewResponseDTO save(ReviewRequestDTO reviewRequestDTO) {
        Review savedReview = reviewRepository.save(reviewMapper.reviewRequestDTOtoReview(reviewRequestDTO));
        return reviewMapper.reviewToReviewResponseDTO(savedReview);
    }

    public ReviewResponseDTO update(UUID id, ReviewRequestDTO reviewRequestDTO) {
        try {
            Review savedReview = findReviewById(id);
            Review review = reviewMapper.reviewRequestDTOtoReview(reviewRequestDTO);
            review.setId(savedReview.getId());
            review.setCreatedAt(savedReview.getCreatedAt());
            review.setUpdatedAt(savedReview.getUpdatedAt());
            Review updatedReview = reviewRepository.save(review);
            return reviewMapper.reviewToReviewResponseDTO(updatedReview);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating review");
        }
    }

    public void deleteById(UUID id) {
        reviewRepository.delete(findReviewById(id));
    }
}
