package com.performance.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.performance.action.BaseAction;
import com.performance.util.ValidateUtil;


public class RightFilterInterceptor implements Interceptor {

	private static final long serialVersionUID = 4230211839075439660L;

	public void destroy() {
	}

	public void init() {
		
	}
	
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();  
        String currentURL = request.getRequestURI();  
         
		ActionProxy proxy = arg0.getProxy();
		String ns = proxy.getNamespace();
//		String actionName = currentURL.substring(5, currentURL.length());
		String actionName ="";
		if(currentURL.indexOf("/")!=-1){
			actionName=currentURL.substring(currentURL.indexOf("/")+1, currentURL.length());
		}
		//currentURL.substring(5, currentURL.length());
		String actionString=actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
		//String actionName = proxy.getActionName();
		if(ValidateUtil.hasRight(ns, actionString, ServletActionContext.getRequest(), arg0.getAction())){
			return arg0.invoke();
		}
		return "toLogin" ;
	}
}
