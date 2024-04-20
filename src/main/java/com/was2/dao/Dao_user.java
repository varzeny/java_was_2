package com.was2.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import com.was2.model.Model_user;
import com.zaxxer.hikari.HikariDataSource;

public class Dao_user {
	
	HikariDataSource dataSource;

	public Dao_user( ServletContext context ) {
		this.dataSource = (HikariDataSource)context.getAttribute("dataSource");
	}
	
	public Model_user loadData(int num) {
		System.out.println("Dao 시작");
		Model_user user=null;
		
		String sql = "SELECT * FROM user WHERE num=?";
		try( Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql) ){
			stmt.setInt(1, num);
			ResultSet rs = stmt.executeQuery();
			if( rs.next() ) {
				user = new Model_user(
						num,
						rs.getString("id"),
						rs.getString("pw"),
						rs.getLong("world_x"),
						rs.getLong("world_y")
					);
			}
		}catch (Exception e) { System.out.println("오류 발생 : "+e); }
		
		System.out.println("Dao 종료");
		return user;
	}
}
