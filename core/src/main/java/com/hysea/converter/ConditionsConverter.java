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

public class ConditionsConverter implements Converter {
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
        Processes.Conditions res = new Processes.Conditions();
        res.setConditions(new ArrayList<>());
        List<Processes.Conditions.Condition> conditionList = res.getConditions();
        int i = 0;
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            conditionList.add(new Processes.Conditions.Condition());
            conditionList.set(i,(Processes.Conditions.Condition)unmarshallingContext.convertAnother(conditionList.get(i),Processes.Conditions.Condition.class,new ConditionConverter()));
            i++;
            hierarchicalStreamReader.moveUp();
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Processes.Conditions.class;
    }
}


