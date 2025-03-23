package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.ReviewInteraction;

@Repository
public interface ReviewInteractionRepository extends JpaRepository<ReviewInteraction,Long> {
}
