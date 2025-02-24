package raf.diplomski.mmgcritic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import raf.diplomski.mmgcritic.data.dto.PasswordChangeDto;
import raf.diplomski.mmgcritic.data.entities.user.Role;
import raf.diplomski.mmgcritic.data.entities.user.User;
import raf.diplomski.mmgcritic.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers()) ;
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.getUserById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok(userService.getUserByEmail(email));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody User u){
        try{
            return ResponseEntity.ok(userService.addUser(u));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User u){
        try{
            return ResponseEntity.ok(userService.updateUser(u));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    //TODO FIX
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeDto passwordChangeDto){
        try{
            return ResponseEntity.ok(userService.updatePassword(passwordChangeDto.getOldPassword(), passwordChangeDto.getNewPassword()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deletePassword(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @PutMapping("/update-role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id,@RequestBody Role role){
        try {
            return ResponseEntity.ok(userService.updateRole(id, role));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }





}
