package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.games.Game;
import raf.diplomski.mmgcritic.data.entities.games.GameGenre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
   Optional<Game> findAllByTitle(String name);

   List<Game> findAllByTitleContains(String name);

   @Query("SELECT game FROM Game game WHERE :genre MEMBER OF game.gameGenres ORDER BY game.releaseDate DESC LIMIT 25")
   List<Game> getNewGamesByGenre(@Param("genre") GameGenre genre);
   @Query("SELECT game FROM Game game ORDER BY game.releaseDate  DESC   LIMIT 25")
   List<Game> getNewGamesByReleaseDate();
   @Query("SELECT game FROM Game game where game.developer like %:company% ORDER BY game.finalGrade DESC   LIMIT 25")
   List<Game> getTopRatedByDeveloper(@Param("company") String company);

   @Query("Select game from Game game where game.releaseDate>1590467599000 order by game.finalGrade desc LIMIT 25")
   List<Game> getNewGamesByTopRated();

   @Query("SELECT game FROM Game  game where :platform member of game.platforms order by game.releaseDate desc LIMIT 25")
   List<Game> getGamesByPlatform(@Param("platform")String platform);

}
