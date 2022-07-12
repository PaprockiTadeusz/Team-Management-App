package com.example.teammanagementapp.services;

import com.example.teammanagementapp.dto.TaskDTO;
import com.example.teammanagementapp.entities.TaskEntity;
import com.example.teammanagementapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    public Optional<TaskDTO> getSingleTask(String title) {
        return taskRepository.findByTitle(title).map(o -> modelMapper.map(o, TaskDTO.class));
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(o -> modelMapper.map(o, TaskDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<TaskDTO> editTask(TaskDTO taskDTO) {
        taskRepository.save(modelMapper.map(taskDTO, TaskEntity.class));
        return Optional.of(taskDTO);
    }

    public Optional<TaskDTO> editTaskPartially(TaskDTO taskDTO) {
        return taskRepository.findByTitle(taskDTO.getTitle())
                .map(entity -> {
                    Optional.of(taskDTO.getTitle()).ifPresent(entity::setTitle);
                    Optional.of(taskDTO.getDescription()).ifPresent(entity::setDescription);
                    Optional.of(taskDTO.getStatus()).ifPresent(entity::setStatus);
                    Optional.of(taskDTO.getDateOfExection()).ifPresent(entity::setDateOfExection);
                    TaskEntity save = taskRepository.save(entity);
                    return Optional.of(modelMapper.map(save, TaskDTO.class));
                }).orElseGet(Optional::empty);
    }

    public boolean addTask(TaskDTO taskDTO) {
        if (Objects.equals(taskDTO.getTitle(), taskRepository.findByTitle(taskDTO.getTitle()).get().getTitle())) {
            taskRepository.save(modelMapper.map(taskDTO, TaskEntity.class));
            return true;
        }
        return false;

    }
}
