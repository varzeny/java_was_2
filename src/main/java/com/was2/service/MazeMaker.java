package com.was2.service;

import java.util.*;


public class MazeMaker {
	int row;
	int col;
	int[][] maze;
	
	public MazeMaker(int row, int col) {
		this.row = row;
		this.col = col;
		maze = new int[this.row][this.col];
	}
		
	
	public void makeMaze() {
		int cr,cc;
		
		// 모든셀에 벽 두르기
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				maze[i][j] = 15;
			}
		}
		
		
		// 랜덤하게 셀 선택
		Random random =new Random();
		cr = random.nextInt(row);
		cc = random.nextInt(col);
		
		re(cr, cc, 0);
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				maze[i][j]-=16;
			}
		}
		
		show();			
	}
	
	
	public void re(int cr, int cc, int in) {
		maze[cr][cc]+=16;
		List<Integer> nums = new ArrayList<>();
		nums.add(1);nums.add(2);nums.add(4);nums.add(8);
		if(in!=0) {
			maze[cr][cc] -= in;
			nums.remove(Integer.valueOf(in));
		}
		Collections.shuffle(nums);
		for(int i:nums) {
			switch(i) {
			case 1:
				if(cr != 0) {
					if( maze[cr-1][cc] >15 ) { break; }
					maze[cr][cc]-=1;
					re(cr-1, cc, 4);
				}
				break;
			case 2:
				if(cc != col-1) {
					if(maze[cr][cc+1] > 15) { break; }
					maze[cr][cc]-=2;
					re(cr, cc+1, 8);
				}
				break;
			case 4:
				if(cr!=row-1) {
					if(maze[cr+1][cc]>15) { break; }
					maze[cr][cc]-=4;
					re(cr+1, cc, 1);
				}
				break;
			case 8:
				if(cc!=0) {
					if(maze[cr][cc-1]>15) { break; }
					maze[cr][cc]-=8;
					re(cr, cc-1, 2);
				}
				break;
			}
		}

	}
	
	
	public void show() {
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				System.out.printf("%3d", maze[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	
	public void showPic() {
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				System.out.print((maze[i][j] & 1) == 1 ? "+---" : "+   ");
			}System.out.print("+");
			System.out.println();
			
			System.out.print("|");
			for(int j=0; j<col; j++) {
				System.out.print((maze[i][j] & 2) == 2 ? "   |" : "    ");
			}
			System.out.println();
		}
		for(int j=0;j<col;j++) {
			System.out.print("+---");
		}System.out.println("+");

	}
	
	
	public static void main(String[] args) {
		MazeMaker maze = new MazeMaker(10,10);
		maze.makeMaze();
		maze.showPic();

	}

}
