package raf.diplomski.mmgcritic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raf.diplomski.mmgcritic.data.entities.ReviewInteraction;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInteractionDto {
    private Long id;
    private Boolean liked;
    private Long userId;
    private Long reviewID;

    public ReviewInteractionDto(ReviewInteraction reviewInteraction) {
        this.id=reviewInteraction.getId();
        this.liked=reviewInteraction.getLiked();
        this.userId=reviewInteraction.getUser().getId();
        this.reviewID=reviewInteraction.getReview().getId();
    }
}
