package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.ReviewType;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighestGradeReviewMediaDto {
    ReviewType type;

    Long id;
    String name;
    Double voteAverage;
    Long voteCount;
    String imgPath;
    Integer grade;
}
