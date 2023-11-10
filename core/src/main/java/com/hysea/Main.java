package com.hysea;

import com.alibaba.fastjson.JSONObject;
import com.hysea.converter.ProcessesNodeConverter;
import com.hysea.entity.ProcessNode;
import com.hysea.entity.Processes;
import com.hysea.util.FileUtil;
import com.hysea.util.Matchers;
import com.hysea.util.RandomUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lombok.Data;

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

    private static List<String> awaitSelectList;

    public static void main(String[] args) {

        List<ProcessNode> processNodeList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        flushLastTime();

        File file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("")).getPath()+"\\process\\DriversLicenseProcess.xml");
        String str = FileUtil.txt2String(file);
        List<String> steps = Matchers.getStringByRegex("<step>[^<>]*</step>", str).stream().map(s -> s.replaceAll("</?step>","")).collect(Collectors.toList());

        awaitSelectList = steps;

        // 将XML转换为Java对象
        XStream xStream = new XStream(new DomDriver());
//        xStream.addDefaultImplementation(Processes.Process.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Processes.Conditions.class,ProcessNode.class);
//        xStream.addDefaultImplementation(Processes.Conditions.Condition.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Processes.Steps.class,ProcessNode.class);
//        xStream.addDefaultImplementation(Processes.Steps.Step.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Processes.ProcessStep.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Processes.Disorder.class,ProcessNode.class);
//        xStream.alias("conditions",Processes.Conditions.class);
////        xStream.addImplicitCollection(Processes.Conditions.class,"conditions");
//        xStream.alias("condition",Processes.Conditions.Condition.class);
//        xStream.alias("steps",Processes.Steps.class);
////        xStream.addImplicitCollection(Processes.Steps.class,"steps");
//        xStream.alias("step",Processes.Steps.Step.class);
//        xStream.alias("processes",Processes.class);
////        xStream.addImplicitCollection(Processes.class,"processes");
//        xStream.alias("process",Processes.Process.class);
////        xStream.aliasField("id", Processes.Process.class, "processId");
//        xStream.alias("disorder",Processes.Disorder.class);
//        xStream.alias("process-step",Processes.ProcessStep.class);

        // 新增这一行，根据类型添加安全权限
        xStream.allowTypes(new Class[]{
                Processes.Conditions.class,
                Processes.Conditions.Condition.class,
                Processes.Steps.class,
                Processes.Steps.Step.class,
                Processes.class,
                Processes.Process.class,
                Processes.Disorder.class,
                Processes.ProcessStep.class,
                ProcessNode.class
        });

        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(new Class[]{
                Processes.Conditions.class,
                Processes.Conditions.Condition.class,
                Processes.Steps.class,
                Processes.Steps.Step.class,
                Processes.class,
                Processes.Process.class,
                Processes.Disorder.class,
                Processes.ProcessStep.class,
                ProcessNode.class
        });
        xStream.registerConverter(new ProcessesNodeConverter());
//        xStream.allowTypesByRegExp(new String[] { ".*" });
//        xStream.ignoreUnknownElements();

        Processes process = (Processes) xStream.fromXML(str);

        List<Processes.Process> processes = process.getProcesses();

        Processes.Process way3 = processes.stream().filter(s -> s.getProcessId().equals("way3")).collect(Collectors.toList()).get(0);

        //输出Java对象
        System.out.println(JSONObject.toJSONString(process));

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

        try {
            way3.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void flushLastTime(){
        Main.lastTime = System.currentTimeMillis();
    }

    public static List<String> getAwaitSelectList() {
        return awaitSelectList;
    }

    public static void setIsExit(boolean isExit) {
        Main.isExit = isExit;
    }
}