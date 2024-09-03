package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.RegisterDto;
import raf.diplomski.mmgcritic.data.dto.UserDto;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.data.mapper.UserMapper;
import raf.diplomski.mmgcritic.repositories.UserRepository;
import raf.diplomski.mmgcritic.services.UserService;
import raf.diplomski.mmgcritic.utils.SpringSecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

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


    //TODO fix and add ifs later
    @Override
    public Boolean updatePassword(String oldPassword,String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(SpringSecurityUtil.getPrincipalEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;

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

    @Override
    public UserDto updateRole(Long id, Role role) {
       User u=userRepository.findById(id).orElseThrow();
       u.setRole(role);
       return userMapper.toDto(userRepository.save(u))  ;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + username + " not found."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

    }
}
