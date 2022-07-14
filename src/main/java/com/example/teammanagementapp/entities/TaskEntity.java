package com.example.teammanagementapp.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task")
@Builder

public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;

    private String description;

    private int status;
    @OneToMany(mappedBy = "id")
    private List<UserEntity> users;

    private LocalDate dateOfExection;


}
