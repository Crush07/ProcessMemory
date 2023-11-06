package com.hysea;

import com.hysea.entity.ProcessNode;
import com.hysea.util.FileUtil;
import com.hysea.util.Matchers;
import com.hysea.util.RandomUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final int waitTime = 10000;

    private static volatile boolean isExit = false;

    private static volatile Long lastTime;

    public static void main(String[] args) {

        List<ProcessNode> processNodeList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        flushLastTime();


        File file = new File("G:\\project\\processMemory\\core\\src\\main\\resources\\process\\DriversLicenseProcess.xml");
        String str = FileUtil.txt2String(file);
        List<String> steps = Matchers.getStringByRegex("<step>[^<>]*</step>", str).stream().map(s -> s.replaceAll("</?step>","")).collect(Collectors.toList());

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

        int step = 0;

        while (!isExit) {

            String[] tempSteps = {"","","","","","","","","",""};
            int realStepIndex = (int) (Math.random() * 10);

            List<Integer> excludeIndex = new ArrayList<>();
            excludeIndex.add(step);
            List<Integer> randomIndexAndExclude = RandomUtils.randomIndexAndExclude(steps.size(), excludeIndex, 9);
            randomIndexAndExclude.add(realStepIndex,step);
            for (int i = 0; i < randomIndexAndExclude.size(); i++) {
                tempSteps[i] = steps.get(randomIndexAndExclude.get(i));
            }

            for (int i = 0; i < tempSteps.length; i++) {
                System.out.println(i + ":" + tempSteps[i]);
            }
            ProcessNode processNode = new ProcessNode();

            int i = scanner.nextInt();
            if(realStepIndex != i){
                isExit = true;
            }else{
                processNode.setProcessContent(steps.get(step));
                processNodeList.add(processNode);
            }
            flushLastTime();

            step++;
        }


    }

    private static void flushLastTime(){
        Main.lastTime = System.currentTimeMillis();
    }
}