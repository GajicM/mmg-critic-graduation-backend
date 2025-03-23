package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.ReviewType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Long mediaId;
    private ReviewType type;
    private String comment;
    private Integer grade;
    private Long datePublished;
    private String username;
    private List<ReviewInteractionDto> reviewInteractionList;
}
