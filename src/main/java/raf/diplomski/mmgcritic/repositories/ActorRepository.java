package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.movies.Actor;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

    @Query("SELECT a FROM Actor a JOIN MovieActor ma ON a=ma.actor WHERE ma.movie.id= :movieId")
    List<Actor> findAllByMovieId(@Param("movieId") Long movieId);
}
