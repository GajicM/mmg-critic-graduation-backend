package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Integer id;
    private String title;
    private Double voteAverage;
    private Integer voteCount;
    private String status;
    private Double revenue;
    private Integer runtime;
    private String backdropPath;
    private Integer budget;
    private String homepage;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String posterPath;
    private String tagline;
    private List<MovieGenre> genres;
    private String productionCompanies;
    private String productionCountries;
    private String spokenLanguages;
    private String keywords;
    private List<ReviewDto> reviews;
}
