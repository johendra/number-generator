package com.sample.numbergenerator.service;

import com.sample.numbergenerator.entity.GoalEntity;
import com.sample.numbergenerator.entity.TaskEntity;
import com.sample.numbergenerator.models.TaskStatus;
import com.sample.numbergenerator.repository.GoalRepository;
import com.sample.numbergenerator.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class TaskExecutorService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    TaskRepository taskRepository;

    ScheduledExecutorService executorService = Executors
            .newSingleThreadScheduledExecutor();

    public void doSomething(TaskEntity taskEntity) {
        CompletableFuture.runAsync(() -> {
            for (int i = taskEntity.getGoal(); i > 0; i = i - taskEntity.getStep()) {
                GoalEntity goalEntity = new GoalEntity();
                goalEntity.setTaskId(taskEntity.getId());
                goalEntity.setNumber(i);
                goalRepository.save(goalEntity);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                taskEntity.setStatus(TaskStatus.ERROR.getValue());
                taskRepository.save(taskEntity);
            }
            GoalEntity goalEntity = new GoalEntity();
            goalEntity.setTaskId(taskEntity.getId());
            goalEntity.setNumber(0);
            goalRepository.save(goalEntity);
            taskEntity.setStatus(TaskStatus.SUCCESS.getValue());
            taskRepository.save(taskEntity);
        });
    }
}