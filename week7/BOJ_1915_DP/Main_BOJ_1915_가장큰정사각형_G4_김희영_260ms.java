package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** dp : 현재까지 고려했을 때 가장 큰 사각형의 크기를 저장 */
public class Main_BOJ_1915_가장큰정사각형_G4_김희영_260ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];

		int max = 0;
		int[][] memo = new int[N][M]; // size

		int[] dr = { -1, -1, 0 }; // 좌상, 상, 좌
		int[] dc = { -1, 0, -1 }; // 좌상, 상, 좌

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);

				if (arr[i][j] == '1') {

					memo[i][j] = 1; // size

					boolean s = true;
					int min = 1001;

					for (int k = 0; k < 3; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr < 0 || nc < 0 || nr >= N || nc >= M || arr[nr][nc] != '1') {
							s = false;
							break;
						}
						if (memo[nr][nc] < min)
							min = memo[nr][nc];

					}

					if (s) {
						memo[i][j] = min + 1;
					}
					if (memo[i][j] > max)
						max = memo[i][j];
				}
			}
		}

		System.out.println(max * max);
	} // end of main

} // end of class
