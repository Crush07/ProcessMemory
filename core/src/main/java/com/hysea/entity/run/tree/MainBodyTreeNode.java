package com.hysea.entity.run.tree;

import lombok.Data;

import com.hysea.entity.run.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MainBodyTreeNode {

    List<MainBodyTreeNode> childList;

    MainBodyTreeNode parent;

    public MainBodyTreeNode() {
        this.childList = new ArrayList<>();
        this.parent = null;
    }

    public Process getProcessByProcessId(String processId){

        MainBodyTreeNode parent = getParent();
        while (parent != null){
            parent = parent.getParent();
        }

        return dfsByClazz(Process.class).stream().map(s -> (Process) s).filter(s -> s.getProcessId().equals(processId)).findFirst().orElse(null);

    }

    public List<Object> dfsByClazz(Class clazz){
        List<Object> res = new ArrayList<>();
        for (MainBodyTreeNode mainBodyTreeNode : getChildList()) {
            if(mainBodyTreeNode.getClass().isAssignableFrom(clazz)){
                res.add(mainBodyTreeNode);
                res.addAll(mainBodyTreeNode.dfsByClazz(clazz));
            }
        }
        return res;

    }
}
