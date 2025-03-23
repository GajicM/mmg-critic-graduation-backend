package raf.diplomski.mmgcritic.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.PublicUserDto;
import raf.diplomski.mmgcritic.data.dto.RegisterDto;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    UserDto getUserByEmail(String email);
    User updateUser(User user);
    Boolean deleteUser(Long id);
    Boolean updatePassword(String oldPassword,String newPassword);

    UserDto register(RegisterDto registerDto);

    UserDto updateRole(Long id, Role role);
    PublicUserDto getUserPublicInfoById(Long id);
    PublicUserDto getUserPublicInfoByUsername(String username);
}
