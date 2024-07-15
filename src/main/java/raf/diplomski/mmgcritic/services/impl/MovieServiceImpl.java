package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
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
        return movieRepository.findMovieByName(name).orElseThrow();
    }
    public Movie addNewMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public Movie updateMovie(Movie movie){
        //TODO FIX/ do better
        Movie m=  movieRepository.findById(movie.getId()).orElseThrow();
        m.setDescription(movie.getDescription());
        m.setGenre(movie.getGenre());
        m.setFinalGrade(movie.getFinalGrade());
        m.setReviewList(movie.getReviewList());
        return movieRepository.save(m);
    }
    public Boolean deleteMovie(Long id){
        try{
            movieRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }




}
