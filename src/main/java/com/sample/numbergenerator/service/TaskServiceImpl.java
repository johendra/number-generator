package com.sample.numbergenerator.service;

import com.sample.numbergenerator.entity.GoalEntity;
import com.sample.numbergenerator.entity.TaskEntity;
import com.sample.numbergenerator.models.GeneratedResult;
import com.sample.numbergenerator.models.StepGoal;
import com.sample.numbergenerator.models.Task;
import com.sample.numbergenerator.models.TaskStatus;
import com.sample.numbergenerator.repository.GoalRepository;
import com.sample.numbergenerator.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    TaskExecutorService taskExecutorService;

    @Override
    public String getStatus(UUID uuid) {
        List<TaskEntity> entities = taskRepository.findAllByTaskId(uuid);
        Integer status = entities.stream().map(TaskEntity::getStatus).min(Integer::compare).orElse(TaskStatus.ERROR.getValue());
        return Objects.requireNonNull(TaskStatus.findByValue(status)).getText();
    }

    @Override
    public GeneratedResult getGeneratedNumber(UUID uuid) {
        GeneratedResult generatedResult = new GeneratedResult();
        List<TaskEntity> tasks = taskRepository.findAllByTaskId(uuid);
        if (tasks.size() > 1) {
            generatedResult.setResults(new ArrayList<>());
            for (TaskEntity task : tasks) {
                List<GoalEntity> results = goalRepository.findAllByTaskId(task.getId());
                generatedResult.getResults().add(results.stream().map(GoalEntity::getNumber).collect(Collectors.toList()));
            }
        } else if(!tasks.isEmpty()){
            List<GoalEntity> results = goalRepository.findAllByTaskId(tasks.get(0).getId());
            generatedResult.setResult(results.stream().map(GoalEntity::getNumber).collect(Collectors.toList()));
        }

        return generatedResult;
    }

    @Override
    public Task generateTask(StepGoal goal) {
        UUID uuid = UUID.randomUUID();
        TaskEntity taskEntity = TaskEntity.builder().goal(goal.getGoal())
                .taskId(uuid)
                .step(goal.getStep())
                .status(TaskStatus.IN_PROGRESS.getValue())
                .build();
        TaskEntity some = taskRepository.save(taskEntity);

        taskExecutorService.doSomething(taskEntity);

        Task task = new Task();
        task.setTask(some.getTaskId());
        return task;
    }

    @Override
    public Task generateBulkTask(List<StepGoal> goals) {
        UUID uuid = UUID.randomUUID();
        for (StepGoal goal : goals) {
            TaskEntity taskEntity = TaskEntity.builder().goal(goal.getGoal())
                    .taskId(uuid)
                    .step(goal.getStep())
                    .status(TaskStatus.IN_PROGRESS.getValue())
                    .build();
            taskRepository.save(taskEntity);
            taskExecutorService.doSomething(taskEntity);
        }
        Task task = new Task();
        task.setTask(uuid);
        return task;
    }
}
