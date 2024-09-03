package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.GameDto;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;
import raf.diplomski.mmgcritic.data.mapper.GameMapper;
import raf.diplomski.mmgcritic.repositories.GameRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl {
    private final GameRepository gameRepository;
    private final GameMapper mapper;

    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public GameDto getGameById(Long id) {
        return mapper.toDto(gameRepository.findById(id).orElseThrow());
    }

    public GameDto getGameByName(String name) {
        return mapper.toDto(gameRepository.findAllByTitle(name).orElseThrow());
    }

    public GameDto addNewGame(Game game) {
        return mapper.toDto(gameRepository.save(game));
    }
    public GameDto updateGame(Game game){
        //TODO check what i want to update in future
        Game g=gameRepository.findById(game.getId()).orElseThrow();
        g.setGameGenres(game.getGameGenres());
        g.setPlatforms(game.getPlatforms());
        g.setFinalGrade(game.getFinalGrade());
        g.setVideoUrl(game.getVideoUrl());
        g.setDescription(game.getDescription());
        return mapper.toDto(gameRepository.save(game));
    }

    public boolean deleteGame(Long id) {
        try{
             gameRepository.deleteById(id);
             return true;
        }catch (Exception e){
            return false;
        }

    }
    public List<GameDto> findAllByTitleContaining(String name){
        return gameRepository.findAllByTitleContains(name).stream().map(mapper::toDto).toList();
    }
    public List<GameDto> getNewGamesByGenre(GameGenre genre){
        return gameRepository.getNewGamesByGenre(genre).stream().map(mapper::toDto).toList();
    }
    public List<GameDto> getNewGamesByReleaseDate(){
        return gameRepository.getNewGamesByReleaseDate().stream().map(mapper::toDto).toList();
    }

    public List<GameDto> getTopRatedByDeveloper(String developer){
        return gameRepository.getTopRatedByDeveloper(developer).stream().map(mapper::toDto).toList();
    }
    public List<GameDto> getNewGamesByTopRated(){
        return gameRepository.getNewGamesByTopRated().stream().map(mapper::toDto).toList();
    }
    public List<GameDto> getGamesByPlatform(String platform){
        return gameRepository.getGamesByPlatform(platform).stream().map(mapper::toDto).toList();
    }








}
