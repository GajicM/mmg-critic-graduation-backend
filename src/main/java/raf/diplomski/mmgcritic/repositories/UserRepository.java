package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
