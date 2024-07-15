package raf.diplomski.mmgcritic.data.entities.music;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.Item;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@DiscriminatorValue("Music")
public class Music extends Item {
    private String artist;
    private String recordLabel;
    private MusicGenre musicGenre;
    private String albumCoverUrl;

}
