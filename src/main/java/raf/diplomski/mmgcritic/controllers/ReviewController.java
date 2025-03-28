package raf.diplomski.mmgcritic.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.ReviewType;
import raf.diplomski.mmgcritic.services.ReviewService;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value="/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;


    @GetMapping("/user-id/{userId}")
    public ResponseEntity<?> getReviewsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsForUser(userId));

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/review-id/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.getReviewById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-id/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id){
        try {
            return ResponseEntity.ok(reviewService.deleteReview(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/update-review")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.updateReview(reviewDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/add-review/game/{id}")
    public ResponseEntity<?>  createGameReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDto,id, ReviewType.GAME));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
    @PostMapping("/add-review/movie/{id}")
    public ResponseEntity<?> createMovieReview(@PathVariable Long id,@RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDto,id, ReviewType.MOVIE));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/add-review/music/{id}")
    public ResponseEntity<?> createMusicReview(@PathVariable Long id,@RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDto,id, ReviewType.MUSIC));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/get-review")
    public ResponseEntity<?> getReview(@PathParam("userId") long userId,@PathParam("itemId") Long itemId, @PathParam("type") ReviewType type){
        try {
            return ResponseEntity.ok(reviewService.getReviewForUserAndItem(userId,itemId, type));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/generate-fake-reviews")
    public ResponseEntity<?> createFakeReviews(@PathParam("id") Long id, @PathParam("type") ReviewType type){
        System.out.println(type);
        try {
            return ResponseEntity.ok(reviewService.createFakeReviews(id,type));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/leave-interaction")
    public ResponseEntity<?> leaveInteraction(@PathParam("reviewId") Long reviewId, @PathParam("userId") Long userId, @PathParam("isLiked") Boolean isLiked){
        try {
            return ResponseEntity.ok(reviewService.leaveInteraction(reviewId,userId,isLiked));
        }catch (NoSuchElementException elementException){
            elementException.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }



}
