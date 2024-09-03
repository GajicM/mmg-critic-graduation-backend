package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.dto.LoginDto;
import raf.diplomski.mmgcritic.data.dto.RegisterDto;
import raf.diplomski.mmgcritic.data.dto.TokenDto;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.jwtUtils.JwtUtil;
import raf.diplomski.mmgcritic.services.UserService;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationProvider authenticationProvider;
    private final UserService userService;
    private final JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest) {
        try {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(new TokenDto(jwtUtil.generateToken(userService.getUserByEmail(loginRequest.getEmail()))));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        try{
         return ResponseEntity.ok(userService.register(registerDto));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }


    }

}
