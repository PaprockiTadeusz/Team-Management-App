package com.example.teammanagementapp.controller;


import com.example.teammanagementapp.dto.TaskDTO;
import com.example.teammanagementapp.repository.TaskRepository;
import com.example.teammanagementapp.services.TaskService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<TaskDTO> getSingleTask(@PathVariable String title){
        return taskService.getSingleTask(title)
                .map(taskDTO -> new ResponseEntity<>(taskDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No task with this title " + title, HttpStatus.NOT_FOUND));
    }

}
