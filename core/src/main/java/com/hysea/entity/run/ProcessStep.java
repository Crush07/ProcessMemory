package com.hysea.entity.run;

import com.hysea.Main;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ProcessStep extends Process {
    /**
     * -process : crossTheRoad
     */

    private String mappingProcessId;

    @Override
    public void next() throws Exception {
        setProcessNodeList(Main.processIdProcessMap.get(getMappingProcessId()).getProcessNodeList());
        super.next();
    }
}