package com.was2.dao;

import javax.servlet.*;
import java.sql.*;
import com.zaxxer.hikari.HikariDataSource;



public class Dao_login {
	
	private HikariDataSource dataSource;
	
	public Dao_login(ServletContext context) {
		this.dataSource = (HikariDataSource)context.getAttribute("dataSource");
	}
	
	public int checkUser(String id, String pw) {
		System.out.println("계정 있는지 확인 시작");
		
		String sql = "SELECT num, pw FROM user WHERE id=?";
		
		try ( Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				System.out.println("id 있음");
				String storedPw = rs.getString("pw");
				if(pw.equals(storedPw)) {
					System.out.println("pw 맞음");
					return rs.getInt("num");
				}else {
					System.out.println("pw 틀림");
				}
			}else {
				System.out.println("id 없음");
			}
		}catch(SQLException e) {
			System.out.println("오류 발생");
			e.printStackTrace();
		}
		
		return 0;
	}

}
