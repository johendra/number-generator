package com.sample.numbergenerator.controller;

import com.sample.numbergenerator.models.GeneratedResult;
import com.sample.numbergenerator.models.TaskStatus;
import com.sample.numbergenerator.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/{UUID}/status")
    private String getStatus(@PathVariable("UUID") UUID uuid) {
        return taskService.getStatus(uuid);
    }

    @GetMapping("/{UUID}/status/list")
    private GeneratedResult getResult(@PathVariable("UUID") UUID uuid, @RequestParam("action")  String action){
        return taskService.getGeneratedNumber(uuid);
    }
}
