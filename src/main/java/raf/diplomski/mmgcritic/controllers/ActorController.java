package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.entities.movies.Actor;
import raf.diplomski.mmgcritic.services.ActorService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/actors")
public class ActorController {
    private ActorService actorService;

    @PostMapping("/new-actor")
    public ResponseEntity<?> createActor(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.createActor(actor));
    }
    @PutMapping("/update-actor")
    public ResponseEntity<?> updateActorInfo(@RequestBody Actor actor){
        return ResponseEntity.ok(actorService.updateActorInfo(actor));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable Long id){
        return ResponseEntity.ok(actorService.deleteActor(id));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllActors(){
        return ResponseEntity.ok(actorService.getAllActors());
    }
    @GetMapping("/movie-all/{movieId}")
    public ResponseEntity<?> getAllActorsForMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(actorService.getAllActorsForMovie(movieId));
    }
    @GetMapping("/id/{actorId}")
    public ResponseEntity<?> getActorById(@PathVariable Long actorId){
        return ResponseEntity.ok(actorService.getActorById(actorId));
    }


}
