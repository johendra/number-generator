package com.sample.numbergenerator.service;

import com.sample.numbergenerator.models.GeneratedResult;
import com.sample.numbergenerator.models.StepGoal;
import com.sample.numbergenerator.models.Task;
import com.sample.numbergenerator.models.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    public String getStatus(UUID uuid);

    public GeneratedResult getGeneratedNumber(UUID uuid);

    public Task generateTask(StepGoal goal);

    public Task generateBulkTask(List<StepGoal> goals);

}
