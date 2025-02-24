package raf.diplomski.mmgcritic.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.MovieDto;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieMapper {
    private final ReviewMapper reviewMapper;
    public Movie fromDto(MovieDto dto){
        if (dto == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setVoteAverage(dto.getVoteAverage());
        movie.setVoteCount(dto.getVoteCount());
        movie.setStatus(dto.getStatus());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setRevenue(dto.getRevenue());
        movie.setRuntime(dto.getRuntime());
        movie.setBackdropPath(dto.getBackdropPath());
        movie.setBudget(dto.getBudget());
        movie.setHomepage(dto.getHomepage());
        movie.setImdbId(dto.getImdbId());
        movie.setOriginalLanguage(dto.getOriginalLanguage());
        movie.setOriginalTitle(dto.getOriginalTitle());
        movie.setOverview(dto.getOverview());
        movie.setPopularity(dto.getPopularity());
        movie.setPosterPath(dto.getPosterPath());
        movie.setTagline(dto.getTagline());
        movie.setGenres(dto.getGenres());
        movie.setProductionCompanies(dto.getProductionCompanies());
        movie.setProductionCountries(dto.getProductionCountries());
        movie.setSpokenLanguages(dto.getSpokenLanguages());
        movie.setKeywords(dto.getKeywords());

        // Convert list of ReviewDto to Review
        if (dto.getReviews() != null) {
            List<Review> reviews = dto.getReviews().stream()
                    .map(reviewMapper::fromDto)
                    .collect(Collectors.toList());
            movie.setReviews(reviews);
        }

        return movie;
    }


    public MovieDto toDto(Movie movie){
        if (movie == null) {
            return null;
        }

        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setVoteAverage(movie.getVoteAverage());
        dto.setVoteCount(movie.getVoteCount());
        dto.setStatus(movie.getStatus());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRevenue(movie.getRevenue());
        dto.setRuntime(movie.getRuntime());
        dto.setBackdropPath(movie.getBackdropPath());
        dto.setBudget(movie.getBudget());
        dto.setHomepage(movie.getHomepage());
        dto.setImdbId(movie.getImdbId());
        dto.setOriginalLanguage(movie.getOriginalLanguage());
        dto.setOriginalTitle(movie.getOriginalTitle());
        dto.setOverview(movie.getOverview());
        dto.setPopularity(movie.getPopularity());
        dto.setPosterPath(movie.getPosterPath());
        dto.setTagline(movie.getTagline());
        dto.setGenres(movie.getGenres());
        dto.setProductionCompanies(movie.getProductionCompanies());
        dto.setProductionCountries(movie.getProductionCountries());
        dto.setSpokenLanguages(movie.getSpokenLanguages());
        dto.setKeywords(movie.getKeywords());

        // Convert list of Review to ReviewDto
        if (movie.getReviews() != null) {
            List<ReviewDto> reviewDtos = movie.getReviews().stream()
                    .map(reviewMapper::toDto)
                    .collect(Collectors.toList());
            dto.setReviews(reviewDtos);
        }

        return dto;
    }
}
