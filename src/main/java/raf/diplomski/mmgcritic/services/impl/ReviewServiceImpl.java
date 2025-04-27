package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.bootstrap.DataGenerator;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.ReviewInteraction;
import raf.diplomski.mmgcritic.data.entities.ReviewType;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.data.mapper.ReviewMapper;
import raf.diplomski.mmgcritic.repositories.*;
import raf.diplomski.mmgcritic.services.ReviewService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;
    private final MovieRepository movieRepository;
    private final MusicRepository musicRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final DataGenerator dataGenerator;
    private final ReviewMapper reviewMapper;
    private final ReviewInteractionRepository reviewInteractionRepository;

    @Override
    public List<ReviewDto> getReviewsForUser(Long userId) {
        return reviewRepository.findAllByUserId(userId).stream().map(mapper::toDto).toList();
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
        review.setUser(userRepository.findByUsername(reviewDto.getUsername()).orElseThrow());
        review.setReviewType(type);
        review.setMediaId(id);
        review= reviewRepository.save(review);

        switch(type){
            case  ReviewType.MUSIC:{
               Music m= musicRepository.findById(id).orElseThrow();
               m.getReviews().add(review);
               m.setVoteCount(m.getVoteCount()+1);
                double res=(m.getFinalGrade()*(m.getVoteCount()-1)+review.getGrade())/ m.getVoteCount();
               m.setFinalGrade(res);
               musicRepository.save(m);
               break;
            }
            case ReviewType.GAME:{
               Game g= gameRepository.findById(id).orElseThrow();
                g.getReviews().add(review);
                g.setVoteCount(g.getVoteCount()+1);
                double res=(g.getFinalGrade()*(g.getVoteCount()-1)+review.getGrade())/ g.getVoteCount();
                g.setFinalGrade(res);
                gameRepository.save(g);
               break;
            }
            case MOVIE:{
               Movie m= movieRepository.findById(id).orElseThrow();
                m.getReviews().add(review);
                m.setVoteCount(m.getVoteCount()+1);
                double res=(m.getVoteAverage()*(m.getVoteCount()-1)+review.getGrade())/ m.getVoteCount();
                m.setVoteAverage(res);
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

    @Override
    public Optional<ReviewDto> getReviewForUserAndItem(long userId, Long itemId, ReviewType type) {
        switch(type){
            case  ReviewType.MUSIC:{
                Music m= musicRepository.findById(itemId).orElseThrow();
                for(Review r :m.getReviews()){
                   if(r.getUser().getId()==userId){
                       return Optional.ofNullable(mapper.toDto(r));
                   }
                }
                break;
            }
            case ReviewType.GAME:{
                Game g= gameRepository.findById(itemId).orElseThrow();
                for(Review r :g.getReviews()){
                    if(r.getUser().getId()==userId){
                        return Optional.ofNullable(mapper.toDto(r));
                    }
                }
                break;
            }
            case MOVIE:{
                Movie m= movieRepository.findById(itemId).orElseThrow();
                for(Review r :m.getReviews()){
                    if(r.getUser().getId()==userId){
                        return Optional.ofNullable(mapper.toDto(r));
                    }
                }
                break;
            }
            default:{
                break;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ReviewDto> createFakeReviews(Long id, ReviewType reviewType) {
       List<Review> reviews= dataGenerator.generateReviews(5);
       List<User> users=userRepository.findAll().stream().filter(user -> {
           Random r= new Random();

          return user.getId()<50 &&  user.getId() % (r.nextInt(5)+1)<3;
       }).toList();
       int i=0;
       List<ReviewDto> toReturn=new ArrayList<>();
       for(Review r: reviews){
           i++;
           r.setUser(users.get(i));

           toReturn.add(addReview(reviewMapper.toDto(r),id,reviewType));
       }
        return toReturn;
    }
    @Override
    public ReviewDto leaveInteraction(Long reviewId, Long userId, Boolean isLiked) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        List<ReviewInteraction> list = review.getReviewInteractionList();
        Optional<ReviewInteraction> existingInteraction = list.stream()
                .filter(ri -> Objects.equals(ri.getUser().getId(), userId))
                .findFirst();

        if (existingInteraction.isPresent()) {
            ReviewInteraction single = existingInteraction.get();
            single.setLiked(isLiked);
            reviewInteractionRepository.save(single);
        } else {
            ReviewInteraction single = new ReviewInteraction();
            User u = userRepository.findById(userId).orElseThrow();
            single.setReview(review);
            single.setUser(u);
            single.setLiked(isLiked);
            reviewInteractionRepository.save(single);
            list.add(single);
        }

        return mapper.toDto(review);
    }

}
