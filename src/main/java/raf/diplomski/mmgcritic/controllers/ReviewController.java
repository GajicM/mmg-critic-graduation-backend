package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.diplomski.mmgcritic.services.ReviewService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value="/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
}
