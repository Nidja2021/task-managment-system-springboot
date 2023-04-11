package com.taskmanagmentsystem.tasks.repository;

import com.taskmanagmentsystem.tasks.dto.CommentResponseDto;
import com.taskmanagmentsystem.tasks.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<CommentResponseDto> findByTaskId(UUID taskId);

}
