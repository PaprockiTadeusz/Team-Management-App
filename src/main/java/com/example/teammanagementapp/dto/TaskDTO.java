package com.example.teammanagementapp.dto;

import lombok.*;

import java.time.LocalDate;

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
    //TODO Add users
}
