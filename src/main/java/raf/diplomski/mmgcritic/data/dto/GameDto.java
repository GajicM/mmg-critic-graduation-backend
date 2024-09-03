package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private Long id;
    private String title;
    private String videoUrl;
    private String description;
    private Double finalGrade;
    private List<ReviewDto> reviews;
    private Long releaseDate;
    private String publisher;
    private String developer;
    private List<GameGenre> gameGenres;
    private List<String> platforms;
    private String imageUrl;
    private Double totalSales; //in millions
}
