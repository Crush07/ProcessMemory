package com.hysea.mapper;

import com.hysea.Main;
import com.hysea.converter.ProcessesConverter;
import com.hysea.entity.run.*;
import com.hysea.entity.run.Process;
import com.hysea.util.FileUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class ProcessMapper {

    //文件名映射流程map
    HashMap<String, Processes> fileNameProcessMap = new HashMap<>();

    public void init(){
        //获取流程文件夹下的所有文件
        File file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("")).getPath());
        File[] files = file.listFiles();

        //遍历并转换成流程对象，存入文件名映射流程map
        if (files != null) {
            for (File f : files) {
                String str = FileUtil.txt2String(file);
                fileNameProcessMap.put(f.getName(),xml2Object(str));
            }
        }
    }

    public Processes xml2Object(String xml){
        XStream xStream = new XStream(new DomDriver());
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

        return (Processes) xStream.fromXML(xml);
    }

}
