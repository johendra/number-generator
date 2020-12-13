package com.sample.numbergenerator.models;

import lombok.Data;

import java.util.List;

@Data
public class GeneratedResult {
    private List<Integer> result;

    @Override
    public String toString() {

        return "GeneratedResult{" +
                "result=" + result +
                '}';
    }
 private List<List<Integer>> results;

}
