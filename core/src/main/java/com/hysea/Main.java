package com.hysea;

import com.alibaba.fastjson.JSONObject;
import com.hysea.converter.ProcessesConverter;
import com.hysea.entity.*;
import com.hysea.util.FileUtil;
import com.hysea.util.Matchers;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import com.hysea.entity.Process;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final int waitTime = 10000;

    private static volatile boolean isExit = false;

    private static volatile Long lastTime;

    private static List<String> awaitSelectList;

    public static HashMap<String,Process> processIdProcessMap = new HashMap<>();

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
//        xStream.addDefaultImplementation(Process.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Conditions.class,ProcessNode.class);
//        xStream.addDefaultImplementation(Conditions.Condition.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Steps.class,ProcessNode.class);
//        xStream.addDefaultImplementation(Steps.Step.class,ProcessNode.class);
////        xStream.addDefaultImplementation(ProcessStep.class,ProcessNode.class);
////        xStream.addDefaultImplementation(Disorder.class,ProcessNode.class);
//        xStream.alias("conditions",Conditions.class);
////        xStream.addImplicitCollection(Conditions.class,"conditions");
//        xStream.alias("condition",Conditions.Condition.class);
//        xStream.alias("steps",Steps.class);
////        xStream.addImplicitCollection(Steps.class,"steps");
//        xStream.alias("step",Steps.Step.class);
//        xStream.alias("processes",class);
////        xStream.addImplicitCollection(class,"processes");
//        xStream.alias("process",Process.class);
////        xStream.aliasField("id", Process.class, "processId");
//        xStream.alias("disorder",Disorder.class);
//        xStream.alias("process-step",ProcessStep.class);

        // 新增这一行，根据类型添加安全权限
        xStream.allowTypes(new Class[]{
                Conditions.class,
                Condition.class,
                Steps.class,
                Step.class,
                Processes.class,
                Process.class,
                Disorder.class,
                ProcessStep.class,
                ProcessNode.class
        });

        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(new Class[]{
                Conditions.class,
                Condition.class,
                Steps.class,
                Step.class,
                Processes.class,
                Process.class,
                Disorder.class,
                ProcessStep.class,
                ProcessNode.class
        });
        xStream.registerConverter(new ProcessesConverter());
//        xStream.allowTypesByRegExp(new String[] { ".*" });
//        xStream.ignoreUnknownElements();

        Processes process = (Processes) xStream.fromXML(str);

        List<Process> processes = process.getProcesses();

        Process way3 = processes.stream().filter(s -> s.getProcessId().equals("way3")).collect(Collectors.toList()).get(0);

        Process crossTheRoad = processes.stream().filter(s -> s.getProcessId().equals("crossTheRoad")).collect(Collectors.toList()).get(0);

        processIdProcessMap.put("way3",way3);
        processIdProcessMap.put("crossTheRoad",crossTheRoad);


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