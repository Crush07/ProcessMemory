package com.hysea.converter;

import com.hysea.entity.Processes;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ProcessStepConverter implements Converter {
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
        Processes.ProcessStep res = new Processes.ProcessStep();

        int attributeCount = hierarchicalStreamReader.getAttributeCount();
        for(int i = 0;i < attributeCount;i++){
            String attribute = hierarchicalStreamReader.getAttribute(i);
            String attributeName = hierarchicalStreamReader.getAttributeName(i);
            if (attributeName.equals("process")) {
                res.setMappingProcessId(attribute);
            }
        }
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Processes.ProcessStep.class;
    }
}


