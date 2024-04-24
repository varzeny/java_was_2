package com.was2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.was2.service.Maker_island;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.was2.model.Model_temp1;



@WebServlet("/ctrl_temp1")
public class Ctrl_temp1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Ctrl_temp1() {
        super();
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ctrl_temp1 시작");
		
		ObjectMapper mapper = new ObjectMapper();
		Model_temp1 data = mapper.readValue(request.getReader(), Model_temp1.class);
		
		
        
		Maker_island maker = new Maker_island(data.row, data.col, data.octave, data.per);
		int map[][] = maker.generate();
		for(int r=0; r<data.row; r++) {
			for(int c=0; c<data.col; c++) {
				System.out.printf("%4d", map[r][c]);
			}
			System.out.println();
		}
		

		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write( mapper.writeValueAsString(map) );
        
		System.out.println("ctrl_temp1 종료");
	}

}
