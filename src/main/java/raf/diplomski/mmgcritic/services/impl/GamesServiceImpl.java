package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.repositories.GameRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl {
    private final GameRepository gameRepository;


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public Game getGameByName(String name) {
        return gameRepository.findByName(name).orElseThrow();
    }

    public Game addNewGame(Game game) {
        return gameRepository.save(game);
    }
    public Game updateGame(Game game){
        //TODO check what i want to update in future
        Game g=gameRepository.findById(game.getId()).orElseThrow();
        g.setGameGenres(game.getGameGenres());
        g.setPlatforms(game.getPlatforms());
        g.setFinalGrade(game.getFinalGrade());
        g.setVideoUrl(game.getVideoUrl());
        g.setDescription(game.getDescription());
        return gameRepository.save(game);
    }

    public boolean deleteGame(Long id) {
        try{
             gameRepository.deleteById(id);
             return true;
        }catch (Exception e){
            return false;
        }

    }
}
