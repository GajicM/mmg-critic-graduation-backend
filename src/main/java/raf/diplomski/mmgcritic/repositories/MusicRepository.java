package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.music.Music;

import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long> {
    Optional<Music> findMusicByName(String name);
}
