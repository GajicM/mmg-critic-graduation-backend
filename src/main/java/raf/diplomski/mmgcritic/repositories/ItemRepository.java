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

}
