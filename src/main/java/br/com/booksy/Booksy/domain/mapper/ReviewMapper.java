package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.ReviewDTO;
import br.com.booksy.Booksy.domain.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review reviewDTOtoReview(ReviewDTO reviewDTO);
    ReviewDTO reviewToReviewDTO(Review review);
}
