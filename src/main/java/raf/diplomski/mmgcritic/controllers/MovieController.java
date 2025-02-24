package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
import raf.diplomski.mmgcritic.data.entities.movies.MovieGenre;
import raf.diplomski.mmgcritic.services.impl.MovieServiceImpl;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@CrossOrigin
public class MovieController {
    private final MovieServiceImpl movieService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.findMovieById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> getMovieByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(movieService.findMovieByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new-movie")
    public ResponseEntity<?> addNewMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.addNewMovie(movie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.deleteMovie(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/find-by-name-part/{name}")
    public ResponseEntity<?> getMoviesByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(movieService.findMoviesByNamePart(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/filter-popular-genre/{genre}")
    public ResponseEntity<?> getPopularMoviesByGenre(@PathVariable MovieGenre genre) {
        try {
            return ResponseEntity.ok(movieService.getPopularMoviesByGenre(genre));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/filter-popular-production/{production}")
    public ResponseEntity<?> getPopularMoviesByGenre(@PathVariable String production) {
        try {
            return ResponseEntity.ok(movieService.getPopularMoviesByProduction(production));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/new")
    public ResponseEntity<?> getPopularMoviesByGenre() {
        try {
            return ResponseEntity.ok(movieService.getPopularMoviesByReleaseDate());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/top-rated")
    public ResponseEntity<?> getTopRatedMovies() {
        try {
            return ResponseEntity.ok(movieService.getTopRated());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/most-popular")
    public ResponseEntity<?> getMostPopularRightNow() {
        try {
            return ResponseEntity.ok(movieService.getMostPopular());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }



}
