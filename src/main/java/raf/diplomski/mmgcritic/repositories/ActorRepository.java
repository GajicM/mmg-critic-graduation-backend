package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.movies.Actor;
@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {
}
