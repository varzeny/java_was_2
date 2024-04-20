package com.was2.model;

public class Model_user {
	public int num;
	public String id;
	public String pw;
	public Long world_x;
	public Long world_y;
	
	public Model_user(int num, String id, String pw, Long world_x, Long world_y) {
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.world_x = world_x;
		this.world_y = world_y;
	}
}
