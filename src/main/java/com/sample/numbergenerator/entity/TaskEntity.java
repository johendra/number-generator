package com.sample.numbergenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "TASK_DETAILS")
public class TaskEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private UUID taskId;

    private int step;
    private int goal;
    private int status;

    public TaskEntity() {

    }
}