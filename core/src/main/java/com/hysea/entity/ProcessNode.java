package com.hysea.entity;

import com.hysea.Main;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
public abstract class ProcessNode {

    public abstract void next() throws Exception;

    public int getInput(){
        return new Scanner(System.in).nextInt();
    }

    public abstract OptionCollection getOptionCollection();

    public void parallelNext(List<ProcessNode> processNodeList){

    }

    public void afterNext(){
        Main.flushLastTime();
    }



}
