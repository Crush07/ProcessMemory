package com.hysea.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Steps extends ProcessNode {

    private List<ProcessNode> steps;

    @Override
    public void next() throws Exception {
        for (ProcessNode step : getSteps()) {
            step.next();
        }
    }
}