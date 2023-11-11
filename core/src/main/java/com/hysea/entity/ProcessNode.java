package com.hysea.entity;

import com.hysea.Main;
import lombok.Data;

@Data
public abstract class ProcessNode {

    public abstract void next() throws Exception;

    public void afterNext(){
        Main.flushLastTime();
    }



}
