package raf.diplomski.mmgcritic.data.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.user.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_interactions")
public class ReviewInteraction {
    @Id @GeneratedValue
    private Long id;

    private Boolean liked;

    @ManyToOne(targetEntity= User.class)
    private User user;
    @ManyToOne(targetEntity = Review.class)
    private Review review;

}

