package raf.diplomski.mmgcritic.data.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Review;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Data
public class User {
    @Id @GeneratedValue
    private Long id;
    @NotNull
    private String username;
    private String firstName;
    private String lastName;
    @NotNull @Column(unique = true)
    private String email;



    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    private List<Review> reviews;
}
