package com.was2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ctrl_main")
public class Ctrl_main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Ctrl_main() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	public void destroy() {
		// TODO Auto-generated method stub
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		if( (boolean)ss.getAttribute("token_login") ) {
			System.out.printf("로그인 토큰을 가지고 main.jsp 에 접근함 %d %s\n",(int)ss.getAttribute("num"),(String)ss.getAttribute("id"));
			response.sendRedirect("static/views/main.jsp");
		}else {
			System.out.println("로그인 토큰 없이 main.jsp 에 접근함");
			response.sendRedirect("index");
		}
		
		
	}



}
