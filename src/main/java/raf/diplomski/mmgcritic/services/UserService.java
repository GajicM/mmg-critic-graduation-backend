package raf.diplomski.mmgcritic.services;

import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.RegisterDto;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    UserDto getUserByEmail(String email);
    User updateUser(User user);
    Boolean deleteUser(Long id);
    Boolean updatePassword(String oldPassword,String newPassword);

    UserDto register(RegisterDto registerDto);
}
