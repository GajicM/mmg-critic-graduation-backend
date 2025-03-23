package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<RecentlyReviewedMediaDto> recentlyReviewedMedia;
    private List<HighestGradeReviewMediaDto> highestGradeReviewMedia;
    private List<ReviewDto> reviews;
  }