package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.services.impl.MusicServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/music")
@RequiredArgsConstructor
@CrossOrigin
public class MusicController {
    private final MusicServiceImpl musicService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMusic() {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> getMusicById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(musicService.getMusicById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> getMusicByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(musicService.getMusicByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new-music")
    public ResponseEntity<?> addNewMusic(@RequestBody Music music) {
        try {
            return ResponseEntity.ok(musicService.addNewMusic(music));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-music")
    public ResponseEntity<?> updateMusic(@RequestBody Music music) {
        try {
            return ResponseEntity.ok(musicService.updateMusic(music));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete-music/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(musicService.deleteMusic(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/by-title/{title}")
    public ResponseEntity<?> findAlbumsByTitleContaining(@PathVariable String title){
        return ResponseEntity.ok(musicService.findAlbumsByTitleContaining(title));
    }
    @GetMapping("/newest")
    public ResponseEntity<?> findAlbumsByReleaseDate(){
        return ResponseEntity.ok(musicService.findAlbumsByReleaseDate());
    }
    @GetMapping("by-artist/{artistName}")
    public ResponseEntity<?> findAlbumsByArtistName(@PathVariable String artistName){
        return ResponseEntity.ok(musicService.findAlbumsByArtistName(artistName));
    }
    @GetMapping("/top-rated")
    public ResponseEntity<?> findTopRatedAlbums() {
        return ResponseEntity.ok(musicService.findTopRatedAlbums());
    }
}
