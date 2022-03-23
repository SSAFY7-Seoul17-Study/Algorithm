package study.day5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_19237_어른상어_골3_추준성_144ms {

	static class Shark {
		int r; // 현재 상어 열 위치
		int c; // 현재 상어 행 위치
		int dir; // 현재 방향
		int[][] priority = new int[5][4]; // 상어의 방향 우선 순위

		public Shark(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	private static int[] dr = { 0,-1, 1, 0, 0 }; // 위, 아래, 왼쪽, 오른쪽
	private static int[] dc = { 0, 0, 0,-1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 격자 크기
		int M = Integer.parseInt(st.nextToken()); // 상어 수
		int k = Integer.parseInt(st.nextToken()); // 이동 횟수

		Shark[] sharks = new Shark[M + 1]; // 상어 번호에 따른 상어 정보
		int[][][] board = new int[N][N][2]; // 상어 번호, 냄새 정보 저장 (board에 남는 건 이 두 정보 뿐 - 상어 이동할 때마다 업데이트)

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 0) continue;
				
				// 상어 초기 위치 업데이트
				sharks[num] = new Shark(i, j, 0);				

				// board 초기 정보 업데이트
				board[i][j][0] = num;
				board[i][j][1] = k;
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {			
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= M; i++) { // 1~M번 상어까지
			for (int j = 1; j <= 4; j++) { // 1~4번(위,아래,왼쪽,오른쪽)일 때 우선순위
				st = new StringTokenizer(br.readLine(), " ");
				sharks[i].priority[j][0] = Integer.parseInt(st.nextToken());
				sharks[i].priority[j][1] = Integer.parseInt(st.nextToken());
				sharks[i].priority[j][2] = Integer.parseInt(st.nextToken());
				sharks[i].priority[j][3] = Integer.parseInt(st.nextToken());
			}
		}

		
		// solution
		int time = 0;
		int sharkCnt = M;
		while (time++ < 1000) {

			// 1. 이동
			for (int i = M; i >= 1; i--) {
				int r = sharks[i].r;
				int c = sharks[i].c;
				int dir = sharks[i].dir;
				boolean isSmell = true;
				
				if(r < 0) continue;
				
				for (int j = 0; j < 4; j++) {
					int nextDir = sharks[i].priority[dir][j];
					int nr = r + dr[nextDir];
					int nc = c + dc[nextDir];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

					// 냄새가 없으면 이동(shark 정보 업데이트 & board 정보 업데이트) 후 탈출
					if (board[nr][nc][1] == 0) {
						sharks[i].r = nr;
						sharks[i].c = nc;
						sharks[i].dir = nextDir;

						isSmell = false;
						break;
					}
				}

				// 냄새가 모두 존재하면 나의 냄새가 있는 쪽으로 이동
				if (isSmell) {
					for (int j = 0; j < 4; j++) {
						int nextDir = sharks[i].priority[dir][j];
						int nr = r + dr[nextDir];
						int nc = c + dc[nextDir];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

						// 나의 냄새가 있는 쪽으로 이동
						if (board[nr][nc][0] == i) {
							sharks[i].r = nr;
							sharks[i].c = nc;
							sharks[i].dir = nextDir;
							break;
						}
					}
				}
			}
			
			// 2. board 냄새 정보 업데이트 (전체 k값 1씩 줄인 후, k값이 0이면 삭제)
			
			// 전체 k값 1씩 줄인 후, k값이 0이면 삭제 
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if(board[row][col][1] == 0) continue; // k값 0이면 continue
					if(--board[row][col][1] == 0) board[row][col][0] = 0;
				}
			}

			// 3. 업데이트된 sharks 정보 board에 반영
			for (int i = M; i >= 1; i--) {
				int nr = sharks[i].r;
				int nc = sharks[i].c;
				
				if (nr < 0) continue;
				
				// 해당 위치에 상어가 있고, 냄새가 k라면(==방금 업데이트된 상어라면) 제거 (상어 번호 M부터 업데이트를 시작하고, 작은 번호가 큰 번호를 잡아먹기 때문)
				if(board[nr][nc][0]!=0 && board[nr][nc][1]==k) {
					sharks[board[nr][nc][0]].r = -1000;
					sharkCnt--;
				}
				
				board[nr][nc][0] = i;
				board[nr][nc][1] = k;				
			}
			
			// 4. 종료
			if(sharkCnt == 1) {
				System.out.print(time);
				System.exit(0);
			}
		}

		System.out.print(-1);

	} // end of main

} // end of class
