package raf.diplomski.mmgcritic.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.user.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    private Long id;

    private String comment;

    private Integer grade;

    private Long datePublished;

    @ManyToOne
    private Item item;

    @ManyToOne
    private User user;
}

