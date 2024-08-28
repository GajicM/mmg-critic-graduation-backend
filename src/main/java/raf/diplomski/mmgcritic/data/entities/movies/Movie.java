package raf.diplomski.mmgcritic.data.entities.movies;

import jakarta.persistence.*;
import lombok.*;
import raf.diplomski.mmgcritic.data.entities.Review;

import java.util.List;

@Data
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "vote_average")
    private Double voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "release_date", length = Integer.MAX_VALUE)
    private Long releaseDate;

    @Column(name = "revenue")
    private Double revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "backdrop_path", length = Integer.MAX_VALUE)
    private String backdropPath;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "homepage", length = Integer.MAX_VALUE)
    private String homepage;

    @Column(name = "imdb_id", length = Integer.MAX_VALUE)
    private String imdbId;

    @Column(name = "original_language", length = Integer.MAX_VALUE)
    private String originalLanguage;

    @Column(name = "original_title", length = Integer.MAX_VALUE)
    private String originalTitle;

    @Column(name = "overview", length = Integer.MAX_VALUE)
    private String overview;

    @Column(name = "popularity", length = Integer.MAX_VALUE)
    private Double popularity;

    @Column(name = "poster_path", length = Integer.MAX_VALUE)
    private String posterPath;

    @Column(name = "tagline", length = Integer.MAX_VALUE)
    private String tagline;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "genres", length = Integer.MAX_VALUE)
    private List<MovieGenre> genres;

    @Column(name = "production_companies", length = Integer.MAX_VALUE)
    private String productionCompanies;

    @Column(name = "production_countries", length = Integer.MAX_VALUE)
    private String productionCountries;

    @Column(name = "spoken_languages", length = Integer.MAX_VALUE)
    private String spokenLanguages;

    @Column(name = "keywords", length = Integer.MAX_VALUE)
    private String keywords;

    @OneToMany
    private List<Review> reviews;


}