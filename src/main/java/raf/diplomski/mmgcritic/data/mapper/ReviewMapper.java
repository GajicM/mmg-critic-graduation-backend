package raf.diplomski.mmgcritic.data.mapper;

import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.dto.ReviewInteractionDto;
import raf.diplomski.mmgcritic.data.entities.Review;

import java.util.List;

@Component
public class ReviewMapper {
    public Review fromDto(ReviewDto reviewDto) {
        //TODO MIGHT NEED TO FIX
        return new Review(
                reviewDto.getId(),
                reviewDto.getMediaId(),
                reviewDto.getType(),
                reviewDto.getComment(),
                reviewDto.getGrade(),
                reviewDto.getDatePublished(),
                null,
                List.of()
        );
    }

    public ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getMediaId(),
                review.getReviewType(),
                review.getComment(),
                review.getGrade(),
                review.getDatePublished(),
                review.getUser().getUsername(),
                review.getReviewInteractionList().stream().map(ReviewInteractionDto::new).toList()
        );

    }
}
