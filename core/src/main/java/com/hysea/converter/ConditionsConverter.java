package com.hysea.converter;

import com.hysea.entity.run.Condition;
import com.hysea.entity.run.Conditions;
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
//        for (Process process : processes.getProcesses()) {
//            stringJoiner.add(process.getProcessId());
//        }
//        hierarchicalStreamWriter.setValue(stringJoiner.toString());
//        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Conditions res = new Conditions();
        res.setConditions(new ArrayList<>());
        List<Condition> conditionList = res.getConditions();
        int i = 0;
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            conditionList.add(new Condition());
            conditionList.set(i,(Condition)unmarshallingContext.convertAnother(conditionList.get(i),Condition.class,new ConditionConverter()));


            //维护对象嵌套路径
            res.getChildList().add(conditionList.get(i));

            hierarchicalStreamReader.moveUp();
            i++;
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Conditions.class;
    }
}


