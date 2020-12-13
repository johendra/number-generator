package com.sample.numbergenerator.models;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StepGoal {
    @NotNull
    @Min(1)
    private int step;
    @NotNull
    @Min(1)
    private int goal;
}
