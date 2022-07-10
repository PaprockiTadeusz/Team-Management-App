package com.example.teammanagementapp.repository;

import com.example.teammanagementapp.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {


}
