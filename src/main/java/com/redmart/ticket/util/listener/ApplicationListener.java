package com.redmart.ticket.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.redmart.ticket.service.UserService;

public class ApplicationListener implements ServletContextListener {
	
	@Autowired
	private UserService userService;

    public void contextInitialized(ServletContextEvent event) {
    	
    	System.out.println(userService+"---");
    	Long userCount = userService.getAllUsersCount();
    	System.out.println(userCount+"---");

    }

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}


}
