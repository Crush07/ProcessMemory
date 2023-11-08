package com.hysea;

import com.hysea.entity.Processes;
import com.hysea.entity.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Test {
 
	public static void main(String[] args) {
		
		//xml转对象
		String xml="<User>\n"
				+" <name>peter</name> \n"
				+"  <age>13</age> \n"
				+"  <SEX>男</SEX> \n"
				+" </User>";

		XStream xstream = new XStream(new DomDriver());
		xstream.allowTypes(new Class[]{User.class});
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(User.class);
//		xml = xml.replaceAll("User-Agent","userAgent");

		User info = (User) xstream.fromXML(xml);
		System.out.println(info);



	}
}