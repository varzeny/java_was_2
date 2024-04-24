package com.was2.service;

import com.was2.model.*;


public class Maker_map {
	int LATITUDE, LONGITUDE;
	String ENVIRONMENT, CIVILIZATION;
	
	int[][] map;
	int maxAltitude = 0;
	int count_sea;
	int count_plain;
	int count_mountain;
	

	public Maker_map( int latitude, int longitude, String environment, String civilization ) {
		this.LATITUDE = latitude;
		this.LONGITUDE = longitude;
		this.ENVIRONMENT = environment;
		this.CIVILIZATION = civilization;
		
		// init
		
	}
	
	
	public Model_map generate() {
		
		step_island();
		step_climate();
		
		return new Model_map();
	}
	
	
	public void step_island() {
		Maker_island maker_island = new Maker_island( 100, 100, 5, 0.5f );
		map = maker_island.generate();
		for(int r=0; r<100; r++) {
			for(int c=0; c<100; c++) {
				int tile = map[r][c];
				if( tile > maxAltitude ) { maxAltitude=tile; }
				if( tile < 70  ) { count_sea++; }
				else if( tile < 110 ) { count_plain++; }
				else { count_mountain++; }
			}
		}
	}
	
	
	public void step_climate() {
		
	}
	
	
	public void step_dungeon() {
		
	}
	
}
