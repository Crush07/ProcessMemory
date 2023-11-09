package com.hysea.converter;

import com.hysea.entity.Processes;
import com.hysea.entity.User;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.StringJoiner;

public class UserConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {

        User user = (User) o;
        hierarchicalStreamWriter.startNode("name");
        hierarchicalStreamWriter.setValue(user.getName());
        hierarchicalStreamWriter.endNode();
        hierarchicalStreamWriter.startNode("age");
        hierarchicalStreamWriter.setValue(String.valueOf(user.getAge()));
        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        User res = new User();
        hierarchicalStreamReader.moveDown();
        System.out.println(hierarchicalStreamReader.getNodeName());
        System.out.println(hierarchicalStreamReader.getValue());
        hierarchicalStreamReader.moveUp();
        hierarchicalStreamReader.moveDown();
        System.out.println(hierarchicalStreamReader.getNodeName());
        System.out.println(hierarchicalStreamReader.getValue());
        hierarchicalStreamReader.moveUp();
        return res;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == User.class;
    }
}


