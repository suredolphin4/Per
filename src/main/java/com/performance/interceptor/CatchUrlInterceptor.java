package com.performance.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.performance.service.RightServiceI;
import com.performance.util.ValidateUtil;


public class CatchUrlInterceptor implements Interceptor {

	private static final long serialVersionUID = 6563408965618840075L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		//名字空间
		String ns = proxy.getNamespace();
		//actionName
		String actionName = proxy.getActionName();
		if(!ValidateUtil.isValid(ns)
				||ns.equals("/")){ 
			ns = "" ;
		}
		String url = ns + "/" + actionName ;
		
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		RightServiceI rs = (RightServiceI) ac.getBean("rightService");
		
		rs.appendRightByURL(url);
		return invocation.invoke();
	}
}
