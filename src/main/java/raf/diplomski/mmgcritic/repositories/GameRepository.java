package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.games.Game;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
   Optional<Game> findByName(String name);
}
