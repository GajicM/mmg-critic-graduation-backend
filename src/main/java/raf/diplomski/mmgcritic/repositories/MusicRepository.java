package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.music.Music;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long> {
    Optional<Music> findMusicByTitle(String name);

    List<Music> findAllByTitleContaining(String string);

    @Query("SELECT m from Music m ORDER BY m.releaseDate DESC LIMIT 25")
    List<Music> findNewestAlbums();

    @Query("SELECT m from Music m join Artist a on m.artist=a where a.name like %:artistName% ORDER BY m.releaseDate DESC LIMIT 25")
    List<Music> findAlbumsByArtistName(@Param("artistName") String artistName);

    @Query("select m from Music m ORDER BY m.finalGrade desc LIMIT 25 ")
    List<Music> findTopRatedAlbums();

}
