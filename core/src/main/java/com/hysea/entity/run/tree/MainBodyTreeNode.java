package com.hysea.entity.run.tree;

import com.hysea.entity.run.ProcessStep;
import com.hysea.entity.run.Step;
import com.hysea.entity.run.list.MainBodyList;
import lombok.Data;

import com.hysea.entity.run.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MainBodyTreeNode {

    MainBodyList childList;

    MainBodyTreeNode parent;

    public MainBodyTreeNode() {
        this.childList = new MainBodyList();
        this.parent = null;
    }

    public Process getProcessByProcessId(String processId){

        MainBodyTreeNode parent = getParent();
        MainBodyTreeNode lastParent = this;
        while (parent != null){
            lastParent = parent;
            parent = parent.getParent();
        }

        return lastParent.dfsByClazz(Process.class).stream().map(s -> (Process) s).filter(s -> s.getProcessId().equals(processId)).findFirst().orElse(null);

    }

    public List<ProcessStep> getAllProcessStep(){

        MainBodyTreeNode parent = getParent();
        MainBodyTreeNode lastParent = this;
        while (parent != null){
            lastParent = parent;
            parent = parent.getParent();
        }

        return lastParent.dfsByClazz(ProcessStep.class).stream().map(s -> (ProcessStep) s).collect(Collectors.toList());

    }

    public List<Step> getAllStep(){

        MainBodyTreeNode parent = getParent();
        MainBodyTreeNode lastParent = this;
        while (parent != null){
            lastParent = parent;
            parent = parent.getParent();
        }

        return lastParent.dfsByClazz(Step.class).stream().map(s -> (Step) s).collect(Collectors.toList());

    }

    public List<Object> dfsByClazz(Class clazz){
        List<Object> res = new ArrayList<>();
        for (MainBodyTreeNode mainBodyTreeNode : getChildList()) {
            if(clazz.isAssignableFrom(mainBodyTreeNode.getClass())){
                res.add(mainBodyTreeNode);
            }
            res.addAll(mainBodyTreeNode.dfsByClazz(clazz));
        }
        return res;

    }
}
