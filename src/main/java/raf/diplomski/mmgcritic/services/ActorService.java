package raf.diplomski.mmgcritic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.entities.movies.Actor;
import raf.diplomski.mmgcritic.repositories.ActorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    private ActorRepository actorRepository;
    public Actor createActor(Actor actor){
        try{
            return actorRepository.save(actor);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Actor updateActorInfo(Actor actor){
        Actor a=actorRepository.findById(actor.getId()).orElseThrow();
        a.setFullName(actor.getFullName());
        a.setUrlToImdb(actor.getUrlToImdb());
        a.setImageUrl(actor.getImageUrl());
        return actorRepository.save(a);
    }
    public Boolean deleteActor(Long id){
        try{
            actorRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }
    public List<Actor> getAllActorsForMovie(Long movieId){
        return actorRepository.findAllByMovieId(movieId);
    }
    public Actor getActorById(Long actorId){
        return actorRepository.findById(actorId).orElseThrow();
    }



}
