package com.hysea.entity;
 
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月17日 下午5:00:38 
 * 类说明 
 */
@Data
@XStreamAlias("user")
public class User {

	@XStreamAlias("name")
	String name;

	@XStreamAlias("age")
	Integer age;

}