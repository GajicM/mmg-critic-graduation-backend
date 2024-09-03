package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.MovieDto;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;
import raf.diplomski.mmgcritic.data.mapper.MovieMapper;
import raf.diplomski.mmgcritic.repositories.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl {
    private final MovieRepository movieRepository;
    private final MovieMapper mapper;

    public List<MovieDto> findAllMovies(){
        return movieRepository.findAll().stream().map(mapper::toDto).toList();
    }
    public MovieDto findMovieById(Long id){
        return mapper.toDto(movieRepository.findById(id).orElseThrow());
    }


    public MovieDto findMovieByName(String name){
        return mapper.toDto(movieRepository.findMovieByTitle(name).orElseThrow());
    }
    public MovieDto addNewMovie(Movie movie){
        return mapper.toDto(movieRepository.save(movie));
    }

    public MovieDto updateMovie(Movie movie){
        //TODO FIX/ do better
    /*    Movie m=  movieRepository.findById(movie.getId()).orElseThrow();
        m.setDescription(movie.getDescription());
        m.setGenre(movie.getGenre());
        m.setFinalGrade(movie.getFinalGrade());
        m.setReviewList(movie.getReviewList());
        return movieRepository.save(m);*/
        return mapper.toDto(movie);
    }
    public Boolean deleteMovie(Long id){
        try{
            movieRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public List<MovieDto> findMoviesByNamePart(String name) {
        return movieRepository.findAllByTitleContains(name).stream().map(mapper::toDto).toList();
    }

    public List<MovieDto> getPopularMoviesByGenre(MovieGenre genre) {
        return movieRepository.getPopularMoviesByGenre(genre).stream().map(mapper::toDto).toList();
    }
    public List<MovieDto> getPopularMoviesByReleaseDate() {
        return movieRepository.getPopularMoviesByReleaseDate().stream().map(mapper::toDto).toList();
    }
    public List<MovieDto> getPopularMoviesByProduction(String company) {
        return movieRepository.getPopularMoviesByProductionCompaniesContaining(company).stream().map(mapper::toDto).toList();
    }

    public List<MovieDto> getTopRated() {
        return movieRepository.getTopRated().stream().map(mapper::toDto).toList();
    }
    public List<MovieDto> getMostPopular() {
        return movieRepository.getPopularRightNow().stream().map(mapper::toDto).toList();
    }
}
