package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;
import raf.diplomski.mmgcritic.repositories.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl {
    private final MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }
    public Movie findMovieById(Long id){
        return movieRepository.findById(id).orElseThrow();
    }

    public Movie findMovieByName(String name){
        return movieRepository.findMovieByTitle(name).orElseThrow();
    }
    public Movie addNewMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie){
        //TODO FIX/ do better
    /*    Movie m=  movieRepository.findById(movie.getId()).orElseThrow();
        m.setDescription(movie.getDescription());
        m.setGenre(movie.getGenre());
        m.setFinalGrade(movie.getFinalGrade());
        m.setReviewList(movie.getReviewList());
        return movieRepository.save(m);*/
        return movie;
    }
    public Boolean deleteMovie(Long id){
        try{
            movieRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public List<Movie> findMoviesByNamePart(String name) {
        return movieRepository.findAllByTitleContains(name);
    }

    public List<Movie> getPopularMoviesByGenre(MovieGenre genre) {
        return movieRepository.getPopularMoviesByGenre(genre);
    }
    public List<Movie> getPopularMoviesByReleaseDate() {
        return movieRepository.getPopularMoviesByReleaseDate();
    }
    public List<Movie> getPopularMoviesByProduction(String company) {
        return movieRepository.getPopularMoviesByProductionCompaniesContaining(company);
    }

    public List<Movie> getTopRated() {
        return movieRepository.getTopRated();
    }
    public List<Movie> getMostPopular() {
        return movieRepository.getPopularRightNow();
    }
}
