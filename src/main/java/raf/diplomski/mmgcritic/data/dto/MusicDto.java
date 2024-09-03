package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.music.Artist;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDto {
    private Long id;
    private String imageUrl;
    private String title;
    private Long releaseDate;
    private Artist artist;
    private Long totalTracks;
    private Double finalGrade;
    private List<ReviewDto> reviews;
}
