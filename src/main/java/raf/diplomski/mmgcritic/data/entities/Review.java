package raf.diplomski.mmgcritic.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private Long id;

    private String comment;

    private Integer grade;

    private Long datePublished;

    @ManyToOne
    private Item item;
}

