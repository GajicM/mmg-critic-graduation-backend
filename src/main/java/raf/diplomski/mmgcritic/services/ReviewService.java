package raf.diplomski.mmgcritic.services;

import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;

import java.util.List;

@Service
public interface ReviewService {

    List<ReviewDto> getReviewsForUser(Long userId);

    List<ReviewDto> getReviewsForItem(Long itemId);

    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    ReviewDto addReview(ReviewDto reviewDto);

    Boolean deleteReview(Long id);

    ReviewDto updateReview(ReviewDto reviewDto);
}
