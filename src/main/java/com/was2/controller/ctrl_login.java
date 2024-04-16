package com.was2.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.was2.service.*;
import com.was2.dao.*;


@WebServlet("/ctrl_login")
public class Ctrl_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ctrl_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그인 요청 받음");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		ServletContext context = getServletContext();
		Dao_login dao_login = new Dao_login( context );
		int num = dao_login.checkUser(id, pw);
		if( num != 0) {
			System.out.println("계정 있음");
			request.getSession().setAttribute("token_login", true);
			request.getSession().setAttribute("num", num);
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("pw", pw);
			
			response.sendRedirect("ctrl_main");
			
		}else {
			System.out.println("계정 없음");
			response.sendRedirect("index.jsp");
		}
		
	}

}
