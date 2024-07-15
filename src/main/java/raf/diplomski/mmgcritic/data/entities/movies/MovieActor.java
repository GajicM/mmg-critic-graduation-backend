package raf.diplomski.mmgcritic.data.entities.movies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieActor {
    @Id
    private Integer id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Actor actor;

    private String role;

}
