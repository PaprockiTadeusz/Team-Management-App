package com.example.teammanagementapp.repository;

import com.example.teammanagementapp.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findByTitle(String title);
    Optional<TaskEntity> editTaskStatus(int status);
}
