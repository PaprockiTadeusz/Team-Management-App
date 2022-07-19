package com.example.teammanagementapp.dto;

import com.example.teammanagementapp.entities.UserEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class TaskDTO {

    private String title;
    private String description;
    private int status;
    // 1 - Not started
    // 2 - During planning
    // 3 - In progress
    // 4 - Finished
    // 5 - Stopped
    private LocalDate dateOfExection;

    private List<UserEntity> users;

}
