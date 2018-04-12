package com.performance.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.performance.action.RightAction;
import com.performance.pagemodel.Right;
import com.performance.service.RightServiceI;

@Component
public class InitRightListener implements ApplicationListener,ServletContextAware{
	private static final Logger logger = Logger.getLogger(InitRightListener.class);
	@Resource
	private RightServiceI rightService;
	private ServletContext sc;
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.sc=servletContext;
		
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof ContextRefreshedEvent) {
			List<Right> allRights=rightService.findAllEntities();
			Map<String, Right> map = new HashMap<String, Right>();
			for(Right r : allRights){
				map.put(r.getRighturl(), r);
			}
			if(sc != null){
				sc.setAttribute("all_rights_map", map);
				logger.info("right init  application");
			}
		}
		
	}

}
