package raf.diplomski.mmgcritic.bootstrap;

import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@Component
public class MoviesCsvLoader {
    @Setter
    private BufferedReader bufferedReader;
    private final Resource resource;
    public MoviesCsvLoader(ResourceLoader resourceLoader) {
        resource = resourceLoader.getResource("classpath:TMDB_movie_dataset_v11.csv");
    }
    public List<Movie> readCurrencyFromCsv() {
/*
* "id",             0
* "title",          1
* "vote_average"    2
* ,"vote_count",    3
* "status",         4
* "release_date",   5
* "revenue",        6
* "runtime",        7
* "adult",          8
* "backdrop_path",  9
* "budget",         10
* "homepage",          11
* "imdb_id",        12
* "original_language", 13
* "original_title",     14
* "overview",           15
* "popularity",         16
* "poster_path",        17
* "tagline",            18
* "genres",             19
* "production_companies"    20
* ,"production_countries",  21
* "spoken_languages",       22
* "keywords"                23
*
*
*
    private List<MovieGenre> genre;
    private Integer duration;
    private String productionCompany;
    private String websiteUrl;
    private Long id;
    private String name;
    private String videoUrl;
    private String description;
    private Double finalGrade;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList;
    private Long releaseDate;
* */
        String line;
        String cvsSplitBy = ",";
        List<Movie> movieList = new ArrayList<>();
        boolean hasHeader = true;
        try {
            inititateBufferedReaderStream();
            while ((line = bufferedReader.readLine()) != null) {
                if (hasHeader) {
                    hasHeader = false;
                    continue;
                }

                String[] data = line.split(cvsSplitBy);
                String name=data[1];
                String[] genres=data[19].split("");
                String duration=data[7];
                String productionCompany=data[20];
                String websiteUrl=data[11];
                String imageUrl="https://image.tmdb.org/t/p/w500/"+data[17];
                String videoUrl=data[1];
                String desc=data[15];
                String finalGrade=data[2];
                String releaseDate=data[5];
                String status=data[4];

                movieList.add(new Movie());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }

    private void inititateBufferedReaderStream() {
        try {
            if (this.bufferedReader != null) {
                return;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
