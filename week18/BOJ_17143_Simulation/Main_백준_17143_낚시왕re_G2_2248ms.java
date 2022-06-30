package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17143_낚시왕re_G2_2248ms {
	
	static int R, C, M;
	
	static int[] dr = {-1, 0, 1, 0};//상 우 하 좌
	static int[] dc = {0, 1, 0, -1};
	
	static final int INDEXR = 0;
	static final int INDEXC = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map map = new Map();
		Shark[] sharks = new Shark[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			d = convertDirection(d);
			int z = Integer.parseInt(st.nextToken());
			
			sharks[i] = new Shark(r, c, s, d, z);
			map.m[r][c] = i;
		}
		map.setSharks(sharks);
		
		while (map.moveHunter() <= C) {
			map.hunt();
			map.moveSharks();
		}
		
		System.out.println(map.getTotalHuntSize());
	}//end main
	
	private static int convertDirection(int d) {
		switch (d) {
		case 1: return 0;
		case 2: return 2;
		case 3: return 1;
		default: return 3;
		}
	}

	static class Map {
		private int[][] m;
		private Hunter hunter;
		private Shark[] sharks;
		
		public Map() {
			m = new int[R + 1][C + 1];
			hunter = new Hunter(0, 0);
		}
		
		public int moveHunter() {
			return hunter.move();
		}
		
		private void hunt() {
			int loc = hunter.getLocation();
			
			for (int i = 1; i <= R; i++) {
				if (m[i][loc] > 0) {
					int deleteIndex = m[i][loc];
					hunter.huntShark(sharks[deleteIndex].getSize());
					sharks[deleteIndex] = null;
					break;
				}
			}
		}
		
		public void moveSharks() {
			clearMap();
			
			for (int i = 1; i <= M; i++) {
				if (sharks[i] == null) continue;
				
				int[] loc = sharks[i].move();
				if (m[loc[INDEXR]][loc[INDEXC]] != 0) {
					int compIdx = m[loc[INDEXR]][loc[INDEXC]];
					if (sharks[i].size < sharks[compIdx].size) {
						sharks[i] = null;
						continue;
					}
					sharks[compIdx] = null;
				}
				m[loc[INDEXR]][loc[INDEXC]] = i;
			}
		}
		
		private void clearMap() {
			for (int i = 1; i <= M; i++) {
				if (sharks[i] == null) continue;
				int r = sharks[i].getR();
				int c = sharks[i].getC();
				m[r][c] = 0;
			}
		}

		public void setSharks(Shark[] sharks) {
			this.sharks = sharks;
		}
		
		public int getTotalHuntSize() {
			return hunter.getHuntSize();
		}
	}

	static class Hunter {
		int location, huntSize;

		public Hunter(int location, int huntSize) {
			this.location = location;
			this.huntSize = huntSize;
		}
		
		public int move() {
			return ++this.location;
		}
		
		public void huntShark(int size) {
			this.huntSize += size;
		}
		
		public int getLocation() {
			return this.location;
		}
		
		public int getHuntSize() {
			return this.huntSize;
		}
	}
	
	static class Shark {
		private int r, c, speed, dirIdx, size;

		public Shark(int r, int c, int speed, int dirIdx, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dirIdx = dirIdx;
			this.size = size;
		}
		
		public int[] move() {
			int count = getSpeed();
			
			int nr = getR();
			int nc = getC();
			while (count-- > 0) {
				nr += dr[getDirIdx()];
				nc += dc[getDirIdx()];
				if (nr <= 0 || nr > R || nc <= 0 || nc > C) {
					count++;
					reverseDirection();
					nr += dr[getDirIdx()];
					nc += dc[getDirIdx()];
					continue;
				}
			}
			setR(nr);
			setC(nc);
			return new int[] {nr, nc};
		}
		
		private void reverseDirection() {
			dirIdx = (dirIdx + 2) % 4;
		}
		
		public int getSpeed() {
			return this.speed;
		}
		
		public int getR() {
			return this.r;
		}
		
		public int getC() {
			return this.c;
		}
		
		public void setR(int r) {
			this.r = r;
		}
		
		public void setC(int c) {
			this.c = c;
		}
		
		public int getDirIdx() {
			return this.dirIdx;
		}
		
		public int getSize() {
			return this.size;
		}
	}
	
}//end class

/*
 * 상어, 낚시꾼, 맵
 * 1. 낚시꾼의 이동 -> 끝까지 가면 종료
 * 2. 낚시꾼의 사냥 -> 상어 하나를 맵과 리스트에서 없애줌
 * 3. 상어의 이동 -> 각 상어들의 객체의 r,c 값 갱신 및 맵에 상어 객체 표시
 * 4. 
 * 
 * 
 * 낚시왕의 위치 -> 매 반복문마다 1씩 옆으로
 * 
 * 사냥 -> 해당 열에서 땅과 가까운 상어 하나 없애면서 크기를 더해줌
 * 
 * 이동
 * 	- copy배열에 상어를 이동시킴, 겹치는 위치에 있는 상어는 크기 비교 후 없애줌
 *  - 한 번 이동 후 경계값 확인?
 *  	-> 방향마다 경계값 위치가 정해져 있다.
 *  	-> 그 경계값과 속력을 이용해 계산할 수 있을 듯??
 * 
 * 현재2 +3 -5 +5 -2 -> 15  +, 2, 15 -> 속도 - 현재 / 크기 => 몫 = 2, 나머지 = 3
 * 
 * map이 있는 이유? 사냥, 이동 시 상어 크기 비교
 * map은 index값을 넣자
 * 
 * 
 * */
