package com.hysea.entity.run;

import com.hysea.Main;
import com.hysea.entity.run.tree.MainBodyTreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Consumer;

@Data
public abstract class ProcessNode extends MainBodyTreeNode{

    public abstract void next() throws Exception;

    public void afterNext(){
        Main.flushLastTime();
    }



}
