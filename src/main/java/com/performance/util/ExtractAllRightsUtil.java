package com.performance.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.performance.service.RightServiceI;


public class ExtractAllRightsUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ExtractAllRightsUtil.class);

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-hibernate.xml"});

		RightServiceI rs = (RightServiceI) ac.getBean("rightService");
		
		ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
		
		URL url = loader.getResource("com/performance/action");
		
		logger.info(url.toString());
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "" ;
		for(File f : files){
			fname = f.getName();
			if(fname.endsWith(".class")
					&& !fname.equals("BaseAction.class")){
				logger.info(fname);
				processAction(fname,rs);
			}
		}
	}

	/**
	 * 处理action类,捕获所有url地址,形成权限
	 */
	@SuppressWarnings("rawtypes")
	public  static void processAction(String fname,RightServiceI rs) {
		try {
			String pkgName = "com.performance.action" ;
			String simpleClassName = fname.substring(0, fname.indexOf(".class"));
			String className = pkgName  + "." + simpleClassName ;
			//得到具体类
			Class clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class retType = null ;
			String mname = null ;
			Class[] paramType = null ;
			String url = null ;
			for(Method m : methods){
				
				retType = m.getReturnType();//返回值类型
				mname = m.getName();        //方法名称
				if(mname.startsWith("get")||mname.startsWith("set")){
//					logger.info(mname+"---------------");
				}else{
					logger.info(mname+"---------------");
					
		
				paramType = m.getParameterTypes();//参数类型
				if(!ValidateUtil.isValid(paramType)
						&& Modifier.isPublic(m.getModifiers())){
/*					if(retType == String.class
							&& !ValidateUtil.isValid(paramType)
							&& Modifier.isPublic(m.getModifiers())){
*/					if(mname.equals("execute")){
						url = "/" + simpleClassName ;
					}
					else{
						url = "/" + simpleClassName + "!" + mname +".action" ;
					}
					rs.appendRightByURL(url);
				}
				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

