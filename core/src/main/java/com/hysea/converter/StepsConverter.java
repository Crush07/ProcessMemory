package com.hysea.converter;

import com.hysea.entity.run.*;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.ArrayList;
import java.util.List;

public class StepsConverter implements Converter {
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
        Steps res = new Steps();
        res.setSteps(new ArrayList<>());
        List<ProcessNode> stepList = res.getSteps();
        int i = 0;
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            stepList.add(new ProcessNode() {
                @Override
                public void next() throws Exception {

                }
            });
            if(hierarchicalStreamReader.getNodeName().equals("conditions")){
                stepList.set(i,(Conditions)unmarshallingContext.convertAnother(stepList.get(i),Conditions.class,new ConditionsConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("steps")){
                stepList.set(i,(Steps)unmarshallingContext.convertAnother(stepList.get(i),Steps.class,new StepsConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("step")){
                stepList.set(i,(Step)unmarshallingContext.convertAnother(stepList.get(i),Step.class,new StepConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("disorder")){
                stepList.set(i,(Disorder)unmarshallingContext.convertAnother(stepList.get(i),Disorder.class,new DisorderConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("process-step")){
                stepList.set(i,(ProcessStep)unmarshallingContext.convertAnother(stepList.get(i),ProcessStep.class,new ProcessStepConverter()));
            }

            //维护对象嵌套路径
            res.getChildList().add(stepList.get(i),res);

            hierarchicalStreamReader.moveUp();
            i++;
        }
        return res;

    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Steps.class;
    }
}


