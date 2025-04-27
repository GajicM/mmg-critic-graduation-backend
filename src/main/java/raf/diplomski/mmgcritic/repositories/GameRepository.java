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

   @Query("Select game from Game game where game.releaseDate>1580467599000 order by game.finalGrade desc LIMIT 25")
   List<Game> getNewGamesByTopRated();

   @Query(value = "SELECT id, description, developer, final_grade, image_url, publisher, release_date, title, total_sales, video_url, vote_count" +
           "                FROM public.game g" +
           "                         JOIN public.game_platforms gp ON g.id = gp.game_id" +
           "                WHERE gp.platforms = :platform" +
           "                ORDER BY g.release_date DESC" +
           "                LIMIT 25;", nativeQuery = true)
   List<Game> getGamesByPlatform(@Param("platform")String platform);

}
