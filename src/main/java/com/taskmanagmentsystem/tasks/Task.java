package com.taskmanagmentsystem.tasks;

import com.taskmanagmentsystem.employees.Employee;
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
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "taskId")
    private UUID id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Employee assignedTo;
}
