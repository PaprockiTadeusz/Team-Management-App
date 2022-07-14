package com.example.teammanagementapp.controller;

import com.example.teammanagementapp.dto.UserDTO;
import com.example.teammanagementapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
