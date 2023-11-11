package com.hysea.converter;

import com.hysea.entity.*;
import com.hysea.entity.Process;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.ArrayList;
import java.util.List;

public class ProcessConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {

//        Processes processes = (Processes) o;
//        hierarchicalStreamWriter.startNode("process");
//        StringJoiner stringJoiner = new StringJoiner(",");
//        for (Process process : processes.getProcesses()) {
//            stringJoiner.add(process.getProcessId());
//        }
//        hierarchicalStreamWriter.setValue(stringJoiner.toString());
//        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Process res = new Process();

        //属性
        int attributeCount = hierarchicalStreamReader.getAttributeCount();
        for(int i = 0;i < attributeCount;i++){
            String attribute = hierarchicalStreamReader.getAttribute(i);
            String attributeName = hierarchicalStreamReader.getAttributeName(i);
            if (attributeName.equals("id")) {
                res.setProcessId(attribute);
            }
        }

        //子节点
        res.setProcessNodeList(new ArrayList<>());
        List<ProcessNode> processNodeList = res.getProcessNodeList();
        int i = 0;
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            processNodeList.add(new ProcessNode() {
                @Override
                public void next() throws Exception {

                }
            });
            if(hierarchicalStreamReader.getNodeName().equals("conditions")){
                processNodeList.set(i,(Conditions)unmarshallingContext.convertAnother(processNodeList.get(i),Conditions.class,new ConditionsConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("steps")){
                processNodeList.set(i,(Steps)unmarshallingContext.convertAnother(processNodeList.get(i),Steps.class,new StepsConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("step")){
                processNodeList.set(i,(Step)unmarshallingContext.convertAnother(processNodeList.get(i),Step.class,new StepConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("disorder")){
                processNodeList.set(i,(Disorder)unmarshallingContext.convertAnother(processNodeList.get(i),Disorder.class,new DisorderConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("process-step")){
                processNodeList.set(i,(ProcessStep)unmarshallingContext.convertAnother(processNodeList.get(i),ProcessStep.class,new ProcessStepConverter()));
            }
            i++;
            hierarchicalStreamReader.moveUp();
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Process.class;
    }
}


