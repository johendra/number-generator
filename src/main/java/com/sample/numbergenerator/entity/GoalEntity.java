package com.sample.numbergenerator.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="GOAL_DETAILS")
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private UUID taskId;
    private int number;
}
