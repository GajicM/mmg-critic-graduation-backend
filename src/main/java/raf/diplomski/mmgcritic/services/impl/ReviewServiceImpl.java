package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.ReviewType;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.data.mapper.ReviewMapper;
import raf.diplomski.mmgcritic.repositories.*;
import raf.diplomski.mmgcritic.services.ReviewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;
    private final MovieRepository movieRepository;
    private final MusicRepository musicRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Override
    public List<ReviewDto> getReviewsForUser(Long userId) {
        return reviewRepository.findAllByUser_Id(userId).stream().map(mapper::toDto).toList();
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
    public ReviewDto addReview(ReviewDto reviewDto,Long id, ReviewType type) {
        Review review=mapper.fromDto(reviewDto);
        //TODO uzeti iz spring security utils at some point
        review.setUser(userRepository.findById(reviewDto.getUserId()).orElseThrow());
       review= reviewRepository.save(review);

        switch(type){
            case  ReviewType.MUSIC:{
               Music m= musicRepository.findById(id).orElseThrow();
               m.getReviews().add(review);
               musicRepository.save(m);
               break;
            }
            case ReviewType.GAME:{
               Game g= gameRepository.findById(id).orElseThrow();
                g.getReviews().add(review);
                gameRepository.save(g);
               break;
            }
            case MOVIE:{
               Movie m= movieRepository.findById(id).orElseThrow();
                m.getReviews().add(review);
                movieRepository.save(m);
                break;
            }
            default:{
                break;
            }
        }



        return mapper.toDto(review);

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
