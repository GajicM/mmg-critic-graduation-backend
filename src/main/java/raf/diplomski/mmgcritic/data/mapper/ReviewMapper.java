package raf.diplomski.mmgcritic.data.mapper;

import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
@Component
public class ReviewMapper {
    public Review fromDto(ReviewDto reviewDto) {
        //TODO MIGHT NEED TO FIX
        return new Review(
                reviewDto.getId(),
                reviewDto.getComment(),
                reviewDto.getGrade(),
                reviewDto.getDatePublished(),
                null,
                null
        );
    }

    public ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getComment(),
                review.getGrade(),
                review.getDatePublished(),
                review.getItem().getId(),
                review.getUser().getId()
        );

    }
}
