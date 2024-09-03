package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser_Id(Long userId);


}
