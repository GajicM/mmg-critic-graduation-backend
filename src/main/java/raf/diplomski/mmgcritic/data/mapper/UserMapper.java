package raf.diplomski.mmgcritic.data.mapper;

import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.HighestGradeReviewMediaDto;
import raf.diplomski.mmgcritic.data.dto.PublicUserDto;
import raf.diplomski.mmgcritic.data.dto.RecentlyReviewedMediaDto;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.Review;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.List;

@Component
public class UserMapper {
    private final ReviewMapper reviewMapper;

    public UserMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole());
    }


    public User fromDto(UserDto dto) {
        return new User(0L,
                dto.getUsername(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                "",
                dto.getRole(),
                List.of());

    }
    public PublicUserDto toPublicUserDto(User u, List<RecentlyReviewedMediaDto> recentlyReviewed, List<HighestGradeReviewMediaDto> highestGradeReviews,List<Review> reviews){
       return new PublicUserDto(
               u.getId(),
               u.getUsername(),
               u.getFirstName(),
               u.getLastName(),
               recentlyReviewed,
               highestGradeReviews,
               reviews.stream().map(reviewMapper::toDto).toList()
       );
    }
}
