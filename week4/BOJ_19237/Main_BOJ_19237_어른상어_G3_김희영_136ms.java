package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19237_어른상어_G3_김희영_136ms {

	static class Room {
		int s; // shark
		int t; // time
		int ns; // next shark

		public Room() {
			ns = Integer.MAX_VALUE;
		}
	}

	static class Shark {
		int r;
		int c;
		int d; // 방향
		int[][] priority;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
			priority = new int[5][5];
		}

	}

	private static Room[][] arr;
	private static Shark[] sharks;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		arr = new Room[N][N];
		sharks = new Shark[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = new Room();
				int t = Integer.parseInt(st.nextToken());
				if (t > 0) {
					sharks[t] = new Shark(i, j);
					arr[i][j].s = t;
					arr[i][j].t = K;
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 1; k <= 4; k++) {
					sharks[i].priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		cnt = M;

		// 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽
		int[] dr = { 0, -1, 1, 0, 0 };
		int[] dc = { 0, 0, 0, -1, 1 };

		for (int c = 1; c <= 1000; c++) {

			// 1. 상어 이동 & 겹치는 상어 처리
			for (int i = 1; i <= M; i++) {

				if (sharks[i].r != -1) {

					boolean notMove = true;
					int d = sharks[i].d;

					// 빈 칸 찾기
					for (int j = 1; j <= 4; j++) {
						int td = sharks[i].priority[d][j];
						int tr = sharks[i].r + dr[td];
						int tc = sharks[i].c + dc[td];

						if (0 <= tr && tr < N && 0 <= tc && tc < N) {
							if (arr[tr][tc].t == 0) {
								move(i, tr, tc, td);
								notMove = false;
								break;
							}
						}
					}

					if (notMove) { // 못 찾았다면 자신의 냄새가 있는 칸 찾기
						for (int j = 1; j <= 4; j++) {
							int td = sharks[i].priority[d][j];
							int tr = sharks[i].r + dr[td];
							int tc = sharks[i].c + dc[td];

							if (0 <= tr && tr < N && 0 <= tc && tc < N) {
								if (arr[tr][tc].s == i) {
									move(i, tr, tc, td);
									notMove = false;
									break;
								}
							}
						}
					}
				}

			} // end of for M sharks move

			// 상어가 한 마리만 남으면 이동 종료
			if (cnt == 1) {
				System.out.println(c);
				System.exit(0);
			}

			// 2. 전체 공간 처리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j].t > 0)
						arr[i][j].t--;
				}
			}

			// 3. 상어가 이동한 칸 처리
			for (int i = 1; i <= M; i++) {
				if (sharks[i].r != -1) {
					int nr = sharks[i].r;
					int nc = sharks[i].c;
					arr[nr][nc].s = i;
					arr[nr][nc].t = K;
					arr[nr][nc].ns = Integer.MAX_VALUE;
				}
			}

		} // end of for 1000

		System.out.println(-1);

	} // end of main

	private static void move(int i, int tr, int tc, int td) {

		if (arr[tr][tc].ns > i) { // MAX_VALUE면 내가 처음으로 예약
			sharks[i].r = tr;
			sharks[i].c = tc;
			sharks[i].d = td;
			arr[tr][tc].ns = i;

		} else { // 나보다 작으면 이미 나보다 쎈(수가 작은) 상어가 예약한 칸
			sharks[i].r = -1;
			sharks[i].c = -1;
			cnt--;
		}
	}

}
