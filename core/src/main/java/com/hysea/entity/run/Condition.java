package com.hysea.entity.run;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Condition extends Process {
    /**
     * condition-name : 绿灯闪时已经在斑马线上
     * steps : {"step":"点刹"}
     */
    private String conditionName;

    @Override
    public void next() throws Exception {
        System.out.println(conditionName);
        for (ProcessNode processNode : getProcessNodeList()) {
            processNode.next();
        }
    }
}