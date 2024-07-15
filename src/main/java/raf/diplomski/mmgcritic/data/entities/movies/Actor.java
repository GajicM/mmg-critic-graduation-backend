package raf.diplomski.mmgcritic.data.entities.movies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

    @Id
    private Long id;
    private String fullName;
    private String urlToImdb;
    private String imageUrl;

}
