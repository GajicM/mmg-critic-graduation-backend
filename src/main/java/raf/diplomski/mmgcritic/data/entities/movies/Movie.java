package raf.diplomski.mmgcritic.data.entities.movies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Item;

import java.util.List;

@DiscriminatorValue("Movie")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Movie extends Item {
    @ElementCollection
    private List<MovieGenre> genre;
    private Integer duration;
    private String productionCompany;
    private String websiteUrl;

}
