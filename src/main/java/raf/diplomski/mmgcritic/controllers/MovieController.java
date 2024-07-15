package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.entities.movies.Movie;
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

    @PostMapping("/new-game")
    public ResponseEntity<?> addNewMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.addNewMovie(movie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-game")
    public ResponseEntity<?> updatMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.updateMovie(movie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete-game/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.deleteMovie(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
