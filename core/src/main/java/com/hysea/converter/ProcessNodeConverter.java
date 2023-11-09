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
import java.util.StringJoiner;

public class ProcessNodeConverter implements Converter {
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
        Processes.Process res = new Processes.Process();
        res.setProcessNodeList(new ArrayList<>());
        List<? super ProcessNode> processNodeList = res.getProcessNodeList();
        int i = 0;
        processNodeList.add(new ProcessNode());
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            System.out.println(hierarchicalStreamReader.getNodeName());
//            System.out.println(unmarshallingContext.convertAnother(processNodeList.get(i),Processes.Process.class));
            hierarchicalStreamReader.moveUp();
            i++;
            processNodeList.add(new ProcessNode());
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Processes.Process.class;
    }
}


