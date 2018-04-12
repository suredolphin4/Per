package com.performance.interceptor;

import java.io.IOException;
import java.util.Map;  

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor; 
import com.performance.action.LoginAction;
import com.performance.pagemodel.Json;
import com.performance.util.Constants;

public class SessionIterceptor  extends AbstractInterceptor {
	@Override  
    public String intercept(ActionInvocation actionInvocation) throws Exception {  
        ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();  
        Action action = (Action) actionInvocation.getAction();  
        if (action instanceof LoginAction) {  
            return actionInvocation.invoke();  
        }  
        Object user = session.get(Constants.USER_SESSION);  
        if (user == null) {  
            return "toLogin";  
        } else {  
            return actionInvocation.invoke();  
        }  
    }  
	
	
	private void writeJson(Object object) {
		try {
			String json =JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
