package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.ReviewRequestDTO;
import br.com.booksy.Booksy.domain.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review reviewDTOtoReview(ReviewRequestDTO reviewRequestDTO);
    ReviewRequestDTO reviewToReviewDTO(Review review);
}
