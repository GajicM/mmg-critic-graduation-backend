package raf.diplomski.mmgcritic.data.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id @GeneratedValue
    private Long id;

    private Long mediaId;
    private ReviewType reviewType;
    private String comment;

    private Integer grade;

    private Long datePublished;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "review", orphanRemoval = true)
    private List<ReviewInteraction> reviewInteractionList;
}

