package com.hysea.converter;

import com.hysea.entity.Process;
import com.hysea.entity.ProcessStep;
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
//        for (Process process : processes.getProcesses()) {
//            stringJoiner.add(process.getProcessId());
//        }
//        hierarchicalStreamWriter.setValue(stringJoiner.toString());
//        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        ProcessStep res = new ProcessStep();

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
        return aClass == ProcessStep.class;
    }
}


