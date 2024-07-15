package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


    Optional<Movie> findMovieByName(String name);
}
