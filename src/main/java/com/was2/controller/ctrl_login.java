package com.was2.controller;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.was2.service.*;
import com.was2.dao.*;


@WebServlet("/ctrl_login")
@MultipartConfig
public class Ctrl_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ctrl_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ctrl_login 서블릿 시작");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id);
		System.out.println(pw);
		
		ServletContext context = getServletContext();
		Dao_login dao_login = new Dao_login( context );
		int num = dao_login.checkUser(id, pw);
		System.out.println(num);
		switch(num) {
		case -1:
			response.getWriter().write("no id !");
			break;
		case -2:
			response.getWriter().write("wrong pw !");
			break;
		case 0:
			response.getWriter().write("DB search error !");
			break;
		default:
			request.getSession().setAttribute("token_login", true);
			request.getSession().setAttribute("num", num);
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("pw", pw);
			
			
			response.getWriter().write("login success !");
			break;
		}
		System.out.println("ctrl_login 서블릿 종료");
	}

}
