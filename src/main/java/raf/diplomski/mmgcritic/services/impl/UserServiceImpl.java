package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.RegisterDto;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.data.mapper.UserMapper;
import raf.diplomski.mmgcritic.repositories.UserRepository;
import raf.diplomski.mmgcritic.services.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder encoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }


    //TODO
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow());
    }

    @Override
    public User updateUser(User user) {
        User u= userRepository.findById(user.getId()).orElseThrow();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(u);
    }

    @Override
    public Boolean deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    //TODO
    @Override
    public Boolean updatePassword(String oldPassword,String newPassword) {
        return null;
    }

    //TODO
    @Override
    public UserDto register(RegisterDto registerDto) {
        User u = new User();
        u.setReviews(List.of());
        u.setEmail(registerDto.getEmail());
        u.setFirstName(registerDto.getFirstName());
        u.setLastName(registerDto.getLastName());
        u.setRole(Role.USER);
        u.setPassword(encoder.encode(registerDto.getPassword()));
        return userMapper.toDto(userRepository.save(u));
    }


}
