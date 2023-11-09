package com.hysea;

import com.hysea.converter.UserConverter;
import com.hysea.entity.User;
import com.thoughtworks.xstream.XStream;

public class UserMain {

    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.registerConverter(new UserConverter());
        xStream.allowTypes(new Class[]{User.class});
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(new Class[]{User.class});

        User user = new User();
        user.setAge(10);
        user.setName("hyy");

        String xml = xStream.toXML(user);
        System.out.println(xml);

        User user1 = (User) xStream.fromXML(xml);
        System.out.println(user1);
    }
}
