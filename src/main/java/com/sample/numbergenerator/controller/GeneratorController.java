package com.sample.numbergenerator.controller;

import com.sample.numbergenerator.models.StepGoal;
import com.sample.numbergenerator.models.Task;
import com.sample.numbergenerator.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class GeneratorController {

    @Autowired
    TaskService taskService;

    @PostMapping("api/generate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task generateStep(@RequestBody @Valid StepGoal goal) {
        return taskService.generateTask(goal);
    }

    @PostMapping("api/bulkGenerate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task generateStep(@RequestBody @Valid List<StepGoal> goal) {
        return taskService.generateBulkTask(goal);
    }
}
