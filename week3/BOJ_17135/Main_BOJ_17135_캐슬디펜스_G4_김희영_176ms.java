package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. nC3를 구해서
 * 2. 해당 조합마다 bfs를 이용해 최대로 제거할 수 있는 적의 수를 구함
 */
public class Main_BOJ_17135_캐슬디펜스_G4_김희영_176ms {

	public static class Arrow implements Comparable<Arrow> {
		int r;
		int c;

		Arrow(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Arrow o) {
			return this.c - o.c;
		}
	}

	private static int max = 0;
	private static int N;
	private static int M;
	private static int D;
	private static int count = 0;
	private static int[] com = new int[3];
	private static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					count++;
			}
		}

		combination(0, 0);
		System.out.println(max);

	} // end of main

	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			simulation();
			return;
		}

		if (start == M)
			return;

		com[cnt] = start;
		combination(cnt + 1, start + 1);
		combination(cnt, start + 1);

	} // end of method combination

	private static void simulation() {

		int cnt = count;
		int sum = 0;
		int[] dr = { 0, -1, 0 };
		int[] dc = { -1, 0, 1 };
		boolean[][] v = new boolean[N][M];

		for (int i = N - 1; i >= 0 && cnt > 0; i--) { // i : 궁수가 있는 행 번호 - 1
			int[] arrR = new int[3];
			int[] arrC = new int[3];
			boolean[] find = new boolean[3];

			for (int j = 0; j < 3; j++) { // 궁수 3명
				PriorityQueue<Arrow> q = new PriorityQueue<>();
				q.offer(new Arrow(i, com[j]));
				here: for (int k = 0; k < D; k++) {
					PriorityQueue<Arrow> p = new PriorityQueue<>();
					while (!q.isEmpty()) {
						int r = q.peek().r;
						int c = q.poll().c;

						if (arr[r][c] == 1 && !v[r][c]) {
							find[j] = true;
							arrR[j] = r;
							arrC[j] = c;
							break here;
						}

						for (int x = 0; x < 3; x++) {
							int nr = r + dr[x];
							int nc = c + dc[x];
							if (0 <= nr && 0 <= nc && nc < M) {
								p.offer(new Arrow(nr, nc));
							}
						}
					}
					q = p;
				}
			}

			for (int j = 0; j < 3; j++) {
				if (find[j] == true) {
					if (!v[arrR[j]][arrC[j]]) {
						v[arrR[j]][arrC[j]] = true;
						sum++;
					}
				}
			}

			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1)
					cnt--;
			}
		}

		if (sum > max)
			max = sum;
	} // end of method simulation

}
