package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_10159_저울_G3_김희영_120ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[][] arr = new boolean[N + 1][N + 1];
		boolean[][] brr = new boolean[N + 1][N + 1];
		StringTokenizer st;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from][to] = true;
			brr[to][from] = true;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (arr[i][k] && arr[k][j])
						arr[i][j] = true;

					if (brr[i][k] && brr[k][j])
						brr[i][j] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;

				if (!arr[i][j] && !brr[i][j])
					sum++;
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb.toString());

	} // end of main

} // end of class
