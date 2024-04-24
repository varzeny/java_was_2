package com.was2.service;

import java.util.Random;


public class Maker_island {
	Random random;
	int seed;
	int row, col;
	int octaveCount;
	float persistence;

	public Maker_island( int row, int col, int octaveCount, float persistence ) {
		random = new Random();
		this.row = row;
		this.col = col;
		this.octaveCount = octaveCount;
		this.persistence = persistence;		
	}
	
	
	public void setting( int row, int col, int octaveCount, float persistence ) {
		this.row = row;
		this.col = col;
		this.octaveCount = octaveCount;
		this.persistence = persistence;		
	}
	
	
	public int[][] generate() {
		float[][] base = step_1_base();
		float[][] perlin = step_2_perlinNoise( base );
		step_3_gradient(perlin);
//		
//		step_modify();
		int[][] result = step_toInt( perlin );
		
		return result;
	}
	
	
	public float[][] step_1_base(){
		float[][] result = new float[row][col];
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				result[r][c]= random.nextFloat(); 
			}
		}
		return result;
	}
	
	
	public float[][] step_2_perlinNoise( float[][] base ){
		float[][] result = new float[row][col];
		
		float octaveLayers[][][] = new float[octaveCount][row][col];
		for(int octave=0; octave<octaveCount; octave++) {
	        //calculate period (wavelength) for different shapes
	        int period = 1 << octave; //2^k
	        float frequency = 1f / period; // 1/2^k
	        
	        for( int r=0; r<row; r++ ) {
	        	int r0 = (r/period)*period;
	        	int r1 = (r0+period)%row;
	        	float verticalBlend = (r-r0)*frequency;
	        	
	        	for( int c=0; c<col; c++ ) {
	        		int c0 = (c/period)*period;
	        		int c1 = (c0 + period)%col;
	        		float horizontalBlend = (c-c0)*frequency;
	        		
	        		float top = interpolate(base[r0][c0], base[r0][c1], horizontalBlend);
	        		float bottom = interpolate(base[r1][c0], base[r1][c1], horizontalBlend);
	        		
	        		octaveLayers[octave][r][c] = interpolate(top, bottom, verticalBlend); 
	        	}
	        }
		}
		
		float amplitude = 1f;
		float totalAmplitude = 0f;
		
		for(int octave = octaveCount-1; octave >=0; octave--) {
			amplitude *= persistence;
			totalAmplitude += amplitude;
			
			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					result[r][c] += octaveLayers[octave][r][c]* amplitude; 
				}
			}
		}
		
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				result[r][c]/= totalAmplitude; 
			}
		}
		
		return result;
	}
	
	
	private float interpolate(float a, float b, float blend) {
	    return a * (1 - blend) + b * blend;
	}
	
	
	public float[][] step_3_gradient( float[][] perlin ){
		int centerX = row / 2;
	    int centerY = col / 2;
	    float maxDist = (float) Math.sqrt(centerX * centerX + centerY * centerY); // 가장자리에서 중심까지의 최대 거리 계산

	    for (int r = 0; r < row; r++) {
	        for (int c = 0; c < col; c++) {
	            float dist = (float) Math.sqrt((r - centerX) * (r - centerX) + (c - centerY) * (c - centerY));
	            float gradient = 1 - dist / maxDist; // 중심에서 멀어질수록 0에 가까워짐
	            gradient = Math.max(gradient, 0); // 음수 값 방지
	            perlin[r][c] *= gradient; // 펄린 노이즈와 그라디언트 적용
	        }
	    }
		return perlin;
	}
	
	
	public int[][] step_toInt( float[][] target ){
		int[][] result = new int[row][col];
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				result[r][c]= (int)(target[r][c]*255);
			}
		}
		return result;
	}
	
	
//	public float[][] step_modify(){
//		
//		return result;
//	}
	
	
//	public static void main(String[] args) {
//		Maker_island maker = new Maker_island();
//		int[][] map = maker.generate(100, 100, 4, 0.5f);
//		
//		for(int r=0; r<100; r++) {
//			for(int c=0; c<100; c++) {
//				System.out.printf("%4d", map[r][c]);
//			}
//			System.out.println();
//		}
//	}

}
