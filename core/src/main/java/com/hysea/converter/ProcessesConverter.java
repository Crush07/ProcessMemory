package com.hysea.converter;

import com.hysea.entity.Processes;
import com.hysea.entity.Process;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ProcessesConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {

        Processes processes = (Processes) o;
        hierarchicalStreamWriter.startNode("process");
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Process process : processes.getProcesses()) {
            stringJoiner.add(process.getProcessId());
        }
        hierarchicalStreamWriter.setValue(stringJoiner.toString());
        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Processes res = new Processes();
        res.setProcesses(new ArrayList<>());
        List<Process> processes = res.getProcesses();
        int i = 0;
        while(hierarchicalStreamReader.hasMoreChildren()){
            hierarchicalStreamReader.moveDown();
            processes.add(new Process());
            processes.set(i,(Process)unmarshallingContext.convertAnother(processes.get(i),Process.class,new ProcessConverter()));
            hierarchicalStreamReader.moveUp();
            i++;;
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Processes.class;
    }
}


