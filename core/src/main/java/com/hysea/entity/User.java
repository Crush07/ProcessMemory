package com.hysea.entity;
 
import com.thoughtworks.xstream.annotations.XStreamAlias;
 
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月17日 下午5:00:38 
 * 类说明 
 */
@XStreamAlias("User")
public class User {
 
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("age")
	private String age;
	@XStreamAlias("SEX")
	private String sex;
	public User(String name, String age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
}