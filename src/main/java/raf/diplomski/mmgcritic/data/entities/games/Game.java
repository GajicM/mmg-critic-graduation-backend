package raf.diplomski.mmgcritic.data.entities.games;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Review;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Game  {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String videoUrl;
    private String description;
    private Double finalGrade;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    private Long releaseDate;
    private String publisher;
    private String developer;
    @ElementCollection @Enumerated(EnumType.STRING)
    private List<GameGenre> gameGenres;
    @ElementCollection
    private List<String> platforms;
    private String imageUrl;
    private Double totalSales; //in millions

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Game game = (Game) o;
        return Objects.equals(title,game.getTitle());
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
