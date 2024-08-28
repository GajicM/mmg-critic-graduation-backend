package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.ReviewType;
import raf.diplomski.mmgcritic.services.ReviewService;

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
            return ResponseEntity.ok(reviewService.addReview(reviewDto,id, ReviewType.MUSIC));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @PostMapping("/add-review/movie/{id}")
    public ResponseEntity<?> createMovieReview(@PathVariable Long id,@RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDto,id, ReviewType.MUSIC));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/add-review/album/{id}")
    public ResponseEntity<?> createMusicReview(@PathVariable Long id,@RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDto,id, ReviewType.MUSIC));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
