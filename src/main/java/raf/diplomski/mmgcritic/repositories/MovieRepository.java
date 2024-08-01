package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


    Optional<Movie> findMovieByTitle(String name);
    List<Movie> findAllByTitleContains(String name);


    @Query("SELECT movie FROM Movie movie WHERE :genre MEMBER OF movie.genres ORDER BY movie.popularity DESC LIMIT 25")
    List<Movie> getPopularMoviesByGenre(@Param("genre") MovieGenre genre);
    @Query("SELECT movie FROM Movie movie ORDER BY movie.releaseDate  DESC   LIMIT 25")
    List<Movie> getPopularMoviesByReleaseDate();
    @Query("SELECT movie FROM Movie movie where movie.productionCompanies like %:company% ORDER BY movie.voteAverage DESC   LIMIT 25")
    List<Movie> getPopularMoviesByProductionCompaniesContaining(@Param("company") String company);

    @Query("Select movie from Movie movie where movie.voteCount>100 order by movie.voteAverage desc LIMIT 25")
    List<Movie> getTopRated();

    @Query("Select movie from Movie movie where movie.releaseDate>1590467599000 order by movie.popularity desc LIMIT 25")
    List<Movie> getPopularRightNow();
}
