package com.hysea;

import com.hysea.entity.ProcessNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final int waitTime = 10000;

    private static volatile boolean isExit = false;

    private static volatile Long lastTime;

    public static void main(String[] args) {

        List<ProcessNode> processNodeList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        flushLastTime();

        //计时线程
        Thread timer = new Thread(() -> {
            while (true) {
                if (System.currentTimeMillis() - Main.lastTime > waitTime) {
                    System.out.println("不合格");
                    isExit = true;
                    break;
                }
            }
        });
        timer.start();

        while (!isExit) {

            ProcessNode processNode = new ProcessNode();
            processNode.setProcessContent(scanner.next());
            processNodeList.add(processNode);
            flushLastTime();

        }


    }

    private static void flushLastTime(){
        Main.lastTime = System.currentTimeMillis();
    }
}