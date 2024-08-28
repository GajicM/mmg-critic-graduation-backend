package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.music.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,String> {
}
