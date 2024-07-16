package raf.diplomski.mmgcritic.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public class Item {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String videoUrl;
    private String description;
    private Double finalGrade;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList;
    private Long releaseDate;
}
