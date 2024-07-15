package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.repositories.ReviewRepository;
import raf.diplomski.mmgcritic.services.ReviewService;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

}
