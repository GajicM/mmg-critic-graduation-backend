package raf.diplomski.mmgcritic.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.MusicDto;
import raf.diplomski.mmgcritic.data.dto.ReviewDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.music.Music;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MusicMapper {
    private final ReviewMapper reviewMapper;
    public Music fromDto(MusicDto dto){
        if (dto == null) {
            return null;
        }

        Music music = new Music();
        music.setId(dto.getId());
        music.setImageUrl(dto.getImageUrl());
        music.setTitle(dto.getTitle());
        music.setReleaseDate(dto.getReleaseDate());
        music.setArtist(dto.getArtist());
        music.setTotalTracks(dto.getTotalTracks());
        music.setFinalGrade(dto.getFinalGrade());

        if (dto.getReviews() != null) {
            List<Review> reviews = dto.getReviews().stream()
                    .map(reviewMapper::fromDto)
                    .collect(Collectors.toList());
            music.setReviews(reviews);
        }

    return music;
    }


    public MusicDto toDto(Music music){
        if (music == null) {
            return null;
        }

        MusicDto dto = new MusicDto();
        dto.setId(music.getId());
        dto.setImageUrl(music.getImageUrl());
        dto.setTitle(music.getTitle());
        dto.setReleaseDate(music.getReleaseDate());
        dto.setArtist(music.getArtist());
        dto.setTotalTracks(music.getTotalTracks());
        dto.setFinalGrade(music.getFinalGrade());

        if (music.getReviews() != null) {
            List<ReviewDto> reviewDtos = music.getReviews().stream()
                    .map(reviewMapper::toDto)
                    .collect(Collectors.toList());
            dto.setReviews(reviewDtos);
        }

        return dto;
    }
}
