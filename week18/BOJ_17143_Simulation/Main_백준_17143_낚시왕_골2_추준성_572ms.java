package problems.시뮬레이션;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_17143_낚시왕_골2_추준성_572ms {
	/*
	 * <설계 주의사항>
	 * - 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 "반대로 바꿔서 속력을 유지한채로 이동"한다.
	 * - 상어가 "이동을 마친 후"에 한 칸에 상어가 두 마리 이상 있을 수 있다.  
	 *   => 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다. (같은 크기인 경우 없음)
	 * 	 => map활용 ("같은 위치"에서 상어를 잡아먹어야 하므로)
	 * 
	 * <설계>
	 * 0. map - ArrayList<Shark>[][] map = new ArrayList[R][C]
	 * 1. 상어 클래스 - r, c, vel, dir, size
	 * 2. 하나의 상어는 두 방향(현재방향, 반대방향)만 이동 가능 => reverseDirection 메소드 생성
	 * 3. 시간초과 => 상어 관리할 자료구조 Queue 만들기
	 * 
	 */
	static class Shark {
		int r, c, vel, dir, size;

		public Shark(int r, int c, int vel, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.vel = vel;
			this.dir = dir;
			this.size = size;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0}; // 상하우좌
	static int[] dc = { 0, 0, 1,-1};
	private static int pc;
	private static Shark[][] map;
	private static int R;
	private static int C;
	private static int M;
	private static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R][C]; // 상어 관리할 map
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int vel = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(r, c, vel, dir, size);
		}
		
		// 낚시왕 위치
		pc = -1;
		
		ans = 0; // 잡은 상어 크기 합

		while(pc++ < C-1) {
			System.out.println();
			getShark();
			moveShark();
		}
		
		System.out.print(ans);
		
	} // end of main

	private static void getShark() {
		for (int i = 0; i < R; i++) {
			if(map[i][pc] == null) continue;
			ans += map[i][pc].size;
			map[i][pc] = null;
			return; // 잡으면 메소드 끝냄
		}
	}

	private static void moveShark() {
		Shark[][] tmpMap = new Shark[R][C]; // 임시저장 map
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == null) continue;
				
				// shark가 있으면 이동하기
				int r = map[i][j].r;
				int c = map[i][j].c;
				int vel = map[i][j].vel;
				int dir = map[i][j].dir;
				int size = map[i][j].size;
				
				map[i][j] = null; // 기존 위치 null
				
				// 상어 이동 (시간초과 주범 => 나머지 연산)
				if(dir == 0 || dir == 1) vel %= (R-1) * 2; // 상하
				else vel %= (C-1) * 2; // 좌우
				
				for (int k = 0; k < vel; k++) {
					r += dr[dir];
					c += dc[dir];
					
					if(r < 0 || r >= R || c < 0 || c >= C) {
						dir = reverseDirection(dir); // 격자 밖으로 나가려고하면 방향 반대로
						// 반대방향으로 한 칸 이동
						r += dr[dir] * 2; 
						c += dc[dir] * 2;
					}
				}
				
				if(tmpMap[r][c] != null && tmpMap[r][c].size > size) continue;
				else tmpMap[r][c] = new Shark(r, c, vel, dir, size);
				
			}
		}
		
		map = tmpMap;
	}
	
	private static int reverseDirection(int dir) {
		switch(dir) {
			case 0:
				dir = 1;
				break;
			case 1:
				dir = 0;
				break;
			case 2:
				dir = 3;
				break;
			case 3:
				dir = 2;
				break;
		}
		return dir;
	}

} // end of class
