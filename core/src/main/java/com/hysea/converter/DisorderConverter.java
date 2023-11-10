package com.hysea.converter;

import com.hysea.entity.ProcessNode;
import com.hysea.entity.Processes;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.ArrayList;
import java.util.List;

public class DisorderConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {

//        Processes processes = (Processes) o;
//        hierarchicalStreamWriter.startNode("process");
//        StringJoiner stringJoiner = new StringJoiner(",");
//        for (Processes.Process process : processes.getProcesses()) {
//            stringJoiner.add(process.getProcessId());
//        }
//        hierarchicalStreamWriter.setValue(stringJoiner.toString());
//        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {

        Processes.Disorder res = new Processes.Disorder();
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
                stepList.set(i,(Processes.Conditions)unmarshallingContext.convertAnother(stepList.get(i),Processes.Conditions.class,new ConditionsConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("steps")){
                stepList.set(i,(Processes.Steps)unmarshallingContext.convertAnother(stepList.get(i),Processes.Steps.class,new StepsConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("step")){
                stepList.set(i,(Processes.Steps.Step)unmarshallingContext.convertAnother(stepList.get(i),Processes.Steps.Step.class,new StepConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("disorder")){
                stepList.set(i,(Processes.Disorder)unmarshallingContext.convertAnother(stepList.get(i),Processes.Disorder.class,new DisorderConverter()));
            }else if(hierarchicalStreamReader.getNodeName().equals("process-step")){
                stepList.set(i,(Processes.ProcessStep)unmarshallingContext.convertAnother(stepList.get(i),Processes.ProcessStep.class,new ProcessStepConverter()));
            }
            i++;
            hierarchicalStreamReader.moveUp();
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Processes.Disorder.class;
    }
}


