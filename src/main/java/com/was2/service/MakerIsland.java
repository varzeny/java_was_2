package com.was2.service;

import java.util.Random;

public class MakerIsland {
	public int[][] map;
	public Random random;
	
	public MakerIsland() {
		random = new Random();
	}
	
	public void makeMap(int latitude, int longitude, int row, int col) {
		this.map = new int[row][col];
		int altitude = random.nextInt(20000) - 10000;
		
		random.nextInt(row);
		random.nextInt(col);
	}
	

	public static void main(String[] args) {
		MakerIsland island = new MakerIsland();

	}

}
