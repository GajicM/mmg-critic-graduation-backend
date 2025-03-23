package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT m.title, 'Music' as type, m.id from Music m where LOWER(m.title) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY m.releaseDate DESC  LIMIT 5" +
            "UNION " +
            "SELECT m.title, 'Movie' as type,  m.id  from Movie m where LOWER(m.title) LIKE LOWER(CONCAT('%', :name, '%'))ORDER BY m.releaseDate DESC  LIMIT 5" +
            "UNION " +
            "SELECT m.title, 'Game' as type, m.id  from Game m where LOWER(m.title) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY m.releaseDate DESC  LIMIT 5"
        )
    List<Object[]> searchAllByTitleWithLimit(@Param("name") String name);


    @Query("SELECT m.title, 'Music' as type, m.id from Music m where LOWER(m.title) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY m.releaseDate DESC " +
            "UNION " +
            "SELECT m.title, 'Movie' as type,  m.id  from Movie m where LOWER(m.title) LIKE LOWER(CONCAT('%', :name, '%'))ORDER BY m.releaseDate DESC  " +
            "UNION " +
            "SELECT m.title, 'Game' as type, m.id  from Game m where LOWER(m.title) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY m.releaseDate DESC "
    )
    List<Object[]> searchAllByTitleNoLimit(@Param("name") String name);


    @Query(value = "(SELECT 'MOVIE' AS type, m.id, m.title, m.vote_average, m.vote_count, m.poster_path, r.date_published " +
            " FROM movies m " +
            " JOIN reviews r ON m.id = r.media_id " +
            " WHERE r.user_id = :userId AND r.review_type = 2) " +
            " UNION " +
            "(SELECT 'GAME' AS type, g.id, g.title, g.final_grade , g.vote_count, g.image_url, r.date_published " +
            " FROM game g " +
            " JOIN reviews r ON g.id = r.media_id " +
            " WHERE r.user_id = :userId AND r.review_type = 0) " +
            " UNION " +
            "(SELECT 'MUSIC' AS type, mm.id, mm.title, mm.final_grade, mm.vote_count, mm.image_url, r.date_published " +
            " FROM music mm " +
            " JOIN reviews r ON mm.id = r.media_id " +
            " WHERE r.user_id = :userId AND r.review_type = 1) " +
            " ORDER BY date_published DESC" ,
            nativeQuery = true)
    List<Object[]> findRecentlyReviewedMediaByUser(@Param("userId") Long userId);

    @Query(value = "(SELECT 'MOVIE' AS type, m.id, m.title, m.vote_average, m.vote_count, m.poster_path, r.grade " +
            " FROM movies m " +
            " JOIN reviews r ON m.id = r.media_id " +
            " WHERE r.review_type = 2 AND r.user_id = :userId " +
            " ORDER BY r.grade DESC " +
            " LIMIT 3) " +
            " UNION " +
            "(SELECT 'GAME' AS type, g.id, g.title, g.final_grade, g.vote_count, g.image_url, r.grade " +
            " FROM game g " +
            " JOIN reviews r ON g.id = r.media_id " +
            " WHERE r.review_type = 0 AND r.user_id = :userId " +
            " ORDER BY r.grade DESC " +
            " LIMIT 3) " +
            " UNION " +
            "(SELECT 'MUSIC' AS type, mm.id, mm.title, mm.final_grade, mm.vote_count, mm.image_url, r.grade " +
            " FROM music mm " +
            " JOIN reviews r ON mm.id = r.media_id " +
            " WHERE r.review_type = 1 AND r.user_id = :userId " +
            " ORDER BY r.grade DESC " +
            " LIMIT 3)",
            nativeQuery = true)
    List<Object[]> findTopReviewedMediaForUser(@Param("userId") Long userId);



}
