package com.was2.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.was2.dao.Dao_user;
import com.was2.model.Model_user;

@WebServlet("/ctrl_loadUser")
public class Ctrl_loadUser extends HttpServlet {
	private static final long serialVersionUID = 1L;     

    public Ctrl_loadUser() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Dao_user dao = new Dao_user( context );
		Model_user user = dao.loadData( (Integer)request.getSession().getAttribute("num") );
		if( user != null ) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(user);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write( jsonString );
		}else {
			response.getWriter().write("no data!");
		}   	
    }

}
