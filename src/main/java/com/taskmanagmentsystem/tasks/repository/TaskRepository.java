package com.taskmanagmentsystem.tasks.repository;

import com.taskmanagmentsystem.tasks.TaskStatus;
import com.taskmanagmentsystem.tasks.dto.TaskResponseDto;
import com.taskmanagmentsystem.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(value = "SELECT * FROM tasks t WHERE t.assigned_to = ?1", nativeQuery = true)
    List<TaskResponseDto> findAllTasksByEmployee(UUID employeeId);

    @Query(value = "SELECT * FROM tasks t WHERE t.status = ?1", nativeQuery = true)
    List<TaskResponseDto> findAllTasksByStatus(TaskStatus taskStatus);
}
