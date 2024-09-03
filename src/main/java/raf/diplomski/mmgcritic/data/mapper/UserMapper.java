package raf.diplomski.mmgcritic.data.mapper;

import org.springframework.stereotype.Component;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.List;

@Component
public class UserMapper {
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
}
