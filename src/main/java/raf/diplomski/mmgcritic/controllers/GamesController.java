package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;
import raf.diplomski.mmgcritic.services.impl.GamesServiceImpl;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@CrossOrigin
public class GamesController {
    private final GamesServiceImpl gamesService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllGames() {
        return ResponseEntity.ok(gamesService.getAllGames());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gamesService.getGameById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> getGameByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(gamesService.getGameByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new-game")
    public ResponseEntity<?> addNewGame(@RequestBody Game game) {
        try {
            return ResponseEntity.ok(gamesService.addNewGame(game));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-game")
    public ResponseEntity<?> updateGame(@RequestBody Game game) {
        try {
            return ResponseEntity.ok(gamesService.updateGame(game));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete-game/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gamesService.deleteGame(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/title-containing/{title}")
    public ResponseEntity<?> findAllByTitleContaining(@PathVariable  String title){

        return ResponseEntity.ok(gamesService.findAllByTitleContaining(title));
    }
    @GetMapping("/new-by-genre/{genre}")
    public ResponseEntity<?>  getNewGamesByGenre(@PathVariable GameGenre genre){
        return ResponseEntity.ok(gamesService.getNewGamesByGenre(genre)) ;
    }
    @GetMapping("/new-by-release")
    public ResponseEntity<?>  getNewGamesByReleaseDate(){
        return ResponseEntity.ok(gamesService.getNewGamesByReleaseDate());
    }
    @GetMapping("/top-by-developer/{developer}")
    public ResponseEntity<?>  getTopRatedByDeveloper(@PathVariable String developer){
        return ResponseEntity.ok(gamesService.getTopRatedByDeveloper(developer));
    }
    @GetMapping("/new-by-top")
    public ResponseEntity<?>  getNewGamesByTopRated(){
        return ResponseEntity.ok(gamesService.getNewGamesByTopRated());
    }
    @GetMapping("/by-platform/{platform}")
    public ResponseEntity<?>  getGamesByPlatform(@PathVariable String platform){
        return ResponseEntity.ok(gamesService.getGamesByPlatform(platform));
    }





}
