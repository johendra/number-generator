package com.sample.numbergenerator.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TaskStatus {
    SUCCESS("SUCCESS", 1), // all these enums give error, for no constructor
    IN_PROGRESS("IN_PROGRESS", 0),
    ERROR("ERROR", -1);

    @Getter
    private final String text;
    @Getter
    private final int value;

    public static TaskStatus findByValue(int val) {
        for (TaskStatus t : values()) {
            if (val == t.getValue()) {
                return t;
            }
        }
        return null;
    }
}
