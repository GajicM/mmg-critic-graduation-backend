package raf.diplomski.mmgcritic.data.entities.games;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Item;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@DiscriminatorValue("Game")
public class Game extends Item {
    private String publisher;
    private String developer;
    @ElementCollection
    private List<GameGenre> gameGenres;
    @ElementCollection
    private List<String> platforms;
}
