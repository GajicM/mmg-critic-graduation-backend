package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
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

    @GetMapping("/item-id/{itemId}")
    public ResponseEntity<?> getReviewsForItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(reviewService.getReviewsForItem(itemId));
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
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto){
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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



}
