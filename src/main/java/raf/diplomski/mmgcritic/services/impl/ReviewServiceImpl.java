package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.mapper.ReviewMapper;
import raf.diplomski.mmgcritic.repositories.ItemRepository;
import raf.diplomski.mmgcritic.repositories.ReviewRepository;
import raf.diplomski.mmgcritic.repositories.UserRepository;
import raf.diplomski.mmgcritic.services.ReviewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public List<ReviewDto> getReviewsForUser(Long userId) {
        return reviewRepository.findAllByUser_Id(userId).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<ReviewDto> getReviewsForItem(Long itemId) {
        return reviewRepository.findAllByItem_Id(itemId).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        return mapper.toDto(reviewRepository.findById(id).orElseThrow());
    }

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review=mapper.fromDto(reviewDto);
        review.setItem(itemRepository.findById(reviewDto.getItemId()).orElseThrow());
        //TODO uzeti iz spring security utils at some point
        review.setUser(userRepository.findById(reviewDto.getUserId()).orElseThrow());
        return  mapper.toDto(reviewRepository.save(review));
    }

    @Override
    public Boolean deleteReview(Long id) {
        try{
            reviewRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review review= reviewRepository.findById(reviewDto.getId()).orElseThrow();
        review.setGrade(reviewDto.getGrade());
        review.setComment(reviewDto.getComment());
        return mapper.toDto(reviewRepository.save(review));
    }
}
