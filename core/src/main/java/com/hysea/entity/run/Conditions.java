package com.hysea.entity.run;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Conditions extends ProcessNode {

    private List<Condition> conditions;

    @Override
    public void next() throws Exception {
        int i = (int) (Math.random() * getConditions().size());
        getConditions().get(i).next();
    }
}