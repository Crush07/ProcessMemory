package com.hysea.converter;

import com.hysea.entity.Processes;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ProcessesNodeConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {

        Processes processes = (Processes) o;
        hierarchicalStreamWriter.startNode("process");
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Processes.Process process : processes.getProcesses()) {
            stringJoiner.add(process.getProcessId());
        }
        hierarchicalStreamWriter.setValue(stringJoiner.toString());
        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Processes res = new Processes();
        res.setProcesses(new ArrayList<>());
        List<Processes.Process> processes = res.getProcesses();
        int i = 0;
        processes.add(new Processes.Process());
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            System.out.println(hierarchicalStreamReader.getNodeName());
            System.out.println(unmarshallingContext.convertAnother(processes.get(i),Processes.Process.class,new ProcessNodeConverter()));
            hierarchicalStreamReader.moveUp();
            i++;
            processes.add(new Processes.Process());
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Processes.class;
    }
}


