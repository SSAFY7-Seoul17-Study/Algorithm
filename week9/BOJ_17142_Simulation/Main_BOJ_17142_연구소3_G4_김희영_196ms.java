package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. cntV개의 바이러스 중 활성시킬 M개의 바이러스를 고름 -> 조합
 * 2. 1번 조합의 모든 경우의 수를 계산하여 min 값 찾기 -> bfs
 * 3. 어떤 경우에도 모든 빈 칸에 바이러스를 퍼트릴 수 없으면 -1 출력
 */
public class Main_BOJ_17142_연구소3_G4_김희영_196ms {

	private static int min = Integer.MAX_VALUE;
	private static char[][] arr;
	private static boolean[][] v;
	private static int N;
	private static int M;
	private static int cntR;
	private static int cntV;
	private static int[][] viruses;
	private static int[] com;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cntR = 0;
		cntV = 0;

		arr = new char[N + 2][N + 2];
		v = new boolean[N + 2][N + 2];
		viruses = new int[2500][2];
		com = new int[M];

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1, index = 0; j <= N; j++, index += 2) {
				arr[i][j] = str.charAt(index);
				switch (arr[i][j]) {
				case '0':
					cntR++;
					break;
				case '2':
					viruses[cntV][0] = i;
					viruses[cntV++][1] = j;
					break;
				}
			}
		}

		if (cntR == 0) {
			System.out.println(0);
			System.exit(0);
		}

		Arrays.fill(arr[0], '1');
		Arrays.fill(arr[N + 1], '1');
		for (int i = 0; i < N + 2; i++) {
			arr[i][0] = '1';
			arr[i][N + 1] = '1';
		}

		combination(0, 0);

		if (min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);

	} // end of main

	private static void combination(int cnt, int start) {
		if (cnt == M) {
			go();
			return;
		}

//		if (start == cntV) {
		if (cntV - M < start - cnt) {
			return;
		}

		com[cnt] = start;
		combination(cnt + 1, start + 1);
		combination(cnt, start + 1);

	}

	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	private static void go() {
		for (int i = 1; i <= N; i++) {
			Arrays.fill(v[i], false);
		}

		Queue<int[]> q = new LinkedList<>(); // r, c, time

		for (int i = 0; i < M; i++) {
			int r = viruses[com[i]][0];
			int c = viruses[com[i]][1];
			v[r][c] = true;
			q.offer(new int[] { r, c, 0 });
		}

		int cnt = cntR;

		while (q.size() > 0) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			int t = tmp[2];

			if (t == min)
				break;
			int nt = t + 1;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (arr[nr][nc] == '1' || v[nr][nc])
					continue;

				v[nr][nc] = true;

				if (arr[nr][nc] == '0') {
					cnt--;
					if (cnt == 0) {
						if (nt < min)
							min = nt;
						return;
					}
				}

				q.offer(new int[] { nr, nc, nt });
			}

		}
	}

} // end of class
