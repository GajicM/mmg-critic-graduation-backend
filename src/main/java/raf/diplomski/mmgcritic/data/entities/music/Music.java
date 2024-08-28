package raf.diplomski.mmgcritic.data.entities.music;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Review;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Music {
    @Id @GeneratedValue
    private Long id;
    private String imageUrl;
    private String title;
    private Long releaseDate;
    @ManyToOne
    private Artist artist;
    private Long totalTracks;
    private Double finalGrade;
    @OneToMany
    private List<Review> reviews;
}
