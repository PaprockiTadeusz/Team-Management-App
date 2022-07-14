package com.example.teammanagementapp.controller;


import com.example.teammanagementapp.dto.TaskDTO;
import com.example.teammanagementapp.repository.TaskRepository;
import com.example.teammanagementapp.services.TaskService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO ) {
        if(taskService.addTask(taskDTO)){

            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Task with this title currently exists", HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping
    public ResponseEntity<TaskDTO> editTask(@RequestBody TaskDTO taskDTO){
        return taskService.editTask(taskDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() ->new ResponseEntity("No task with this title", HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public ResponseEntity<TaskDTO> editTaskPartially(@RequestBody TaskDTO taskDTO){
        return taskService.editTaskPartially(taskDTO)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No task with this title", HttpStatus.NOT_FOUND));
    }
    @PatchMapping
    public ResponseEntity<TaskDTO> editTaskStatus(String title,int status){
        return taskService.editTaskStatus(title,status)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No task with this title", HttpStatus.NOT_FOUND));
    }

}
