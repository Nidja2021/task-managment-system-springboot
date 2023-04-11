package com.taskmanagmentsystem.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "commentId")
    private UUID id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
