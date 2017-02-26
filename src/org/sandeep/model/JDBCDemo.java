package org.sandeep.model;

import org.sandeep.dao.JDBCDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JDBCDemo {

	public static void main (String [] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("springResource.xml");
		
		Circle circle = context.getBean("jdbcdao", JDBCDaoImpl.class).getCircle(1);
		System.out.println(circle.getName()+"is the name");
		System.out.println(context.getBean("jdbcdao", JDBCDaoImpl.class).getCount()+"def");
	}
}
