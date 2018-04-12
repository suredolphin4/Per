package com.performance.test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.performance.pagemodel.User;
import com.performance.service.UserServiceI;

public class UserTest {
@Transactional
	@Test
	public  void test(){
//		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
//		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-hibernate.xml"});
//		UserServiceI userService=(UserServiceI) ac.getBean("userService");
//		
//		User user=new User();
//		user.setUserid(0023);
//		user.setPwd("dddddd");
//		userService.save(user);
	}
}
