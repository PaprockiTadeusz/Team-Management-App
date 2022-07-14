package com.example.teammanagementapp.services;

import com.example.teammanagementapp.dto.UserDTO;
import com.example.teammanagementapp.entities.UserEntity;
import com.example.teammanagementapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class ))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDTO.class));
    }

    public boolean addUser(UserDTO user) {
        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            userRepository.save(modelMapper.map(user, UserEntity.class));
            return true;
        }
        return false;
    }

    public Optional<UserDTO> editUser(UserDTO user) {
        userRepository.save(modelMapper.map(user, UserEntity.class));
        return Optional.of(user);
    }

    public Optional<UserDTO> editUserPartially(UserDTO user) {
        return userRepository.findByEmail(user.getEmail())
                .map(entity -> {
                    Optional.of(user.getEmail()).ifPresent(entity::setEmail);
                    Optional.of(user.getName()).ifPresent(entity::setName);
                    Optional.of(user.getSurname()).ifPresent(entity::setSurname);
                    UserEntity save = userRepository.save(entity);
                    return Optional.of(modelMapper.map(save, UserDTO.class));
                })
                .orElseGet(Optional::empty);
    }
}
