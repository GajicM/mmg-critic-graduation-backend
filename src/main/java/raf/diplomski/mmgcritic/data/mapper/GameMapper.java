package raf.diplomski.mmgcritic.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.GameDto;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.games.Game;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameMapper {
    private final ReviewMapper reviewMapper;
    public Game fromDto(GameDto dto){
        if (dto == null) {
            return null;
        }

        Game game = new Game();
        game.setId(dto.getId());
        game.setTitle(dto.getTitle());
        game.setVideoUrl(dto.getVideoUrl());
        game.setDescription(dto.getDescription());
        game.setFinalGrade(dto.getFinalGrade());
        game.setReleaseDate(dto.getReleaseDate());
        game.setPublisher(dto.getPublisher());
        game.setDeveloper(dto.getDeveloper());
        game.setGameGenres(dto.getGameGenres());
        game.setPlatforms(dto.getPlatforms());
        game.setImageUrl(dto.getImageUrl());
        game.setTotalSales(dto.getTotalSales());

        if (dto.getReviews() != null) {
            List<Review> reviews = dto.getReviews().stream()
                    .map(reviewMapper::fromDto)
                    .collect(Collectors.toList());
            game.setReviews(reviews);
        }

        return game;
    }


    public GameDto toDto(Game game){
        if (game == null) {
            return null;
        }

        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setVideoUrl(game.getVideoUrl());
        dto.setDescription(game.getDescription());
        dto.setFinalGrade(game.getFinalGrade());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setPublisher(game.getPublisher());
        dto.setDeveloper(game.getDeveloper());
        dto.setGameGenres(game.getGameGenres());
        dto.setPlatforms(game.getPlatforms());
        dto.setImageUrl(game.getImageUrl());
        dto.setTotalSales(game.getTotalSales());

        if (game.getReviews() != null) {
            List<ReviewDto> reviewDtos = game.getReviews().stream()
                    .map(reviewMapper::toDto)
                    .collect(Collectors.toList());
            dto.setReviews(reviewDtos);
        }

        return dto;
    }
}
