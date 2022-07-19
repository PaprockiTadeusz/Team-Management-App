package com.example.teammanagementapp.controller;

import com.example.teammanagementapp.dto.UserDTO;
import com.example.teammanagementapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getSingleUser(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No user with this email " + email, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        if(userService.addUser(userDTO)){
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity("There is currently user with this email " + userDTO.getEmail(), HttpStatus.BAD_REQUEST );
    }

    @PutMapping
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO userDTO){
        return userService.editUser(userDTO)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No user with this email " + userDTO.getEmail(), HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public ResponseEntity<UserDTO> updateUserPartially(@RequestBody UserDTO userDTO){
        return userService.editUser(userDTO)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No user with this email " + userDTO.getEmail(), HttpStatus.NOT_FOUND));
    }
}
