package com.was2.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zaxxer.hikari.*;


@WebListener
public class MyServletContextListener implements ServletContextListener {


    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }


    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("App Start!!!!");
    	
    	// HikariCP 설정
    	HikariConfig config_hikari = new HikariConfig();
    	config_hikari.setDriverClassName("com.mysql.cj.jdbc.Driver");
    	config_hikari.setJdbcUrl("jdbc:mysql://localhost:3306/prac_db_1");
    	config_hikari.setUsername("dba");
    	config_hikari.setPassword("dba");
    	config_hikari.setMaximumPoolSize(10);
    	config_hikari.setMinimumIdle(5);
    	
    	// HikariCP 데이터 소스 생성 및 등록
    	HikariDataSource dataSource = new HikariDataSource(config_hikari);
    	sce.getServletContext().setAttribute("dataSource", dataSource);
    	System.out.println("DB Open!!");
    	
    }
    
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
        // DB 종료 절차
    	HikariDataSource dataSource = (HikariDataSource)sce.getServletContext().getAttribute("dataSource");
    	if(dataSource != null) {
    		dataSource.close();
    	}
    	System.out.println("DB Closed!!");
    	
    	System.out.println("App End!!!!");
   }
	
}
