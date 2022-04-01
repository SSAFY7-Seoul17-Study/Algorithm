package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 현재 칸에서 갈 수 있는 칸의 방법의 수에
 * 현재 위치까지 올 수 있는 방법의 수를 더한다.
 */
public class Main_BOJ_1890_점프_S2_김희영_80ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < arr.length; j++, index += 2) {
				arr[i][j] = str.charAt(index) - '0';
			}
		}

		long[][] d = new long[N][N];	// i, j 까지 올 수 있는 방법의 합
		d[0][0] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if (d[i][j] == 0)	continue;	// i, j까지 올 수 없으면 계산할 필요 없음
				if (i == N - 1 && j == N - 1)	break;	// 마지막도 계산하면 값이 4배가 됨

				int nr = i + arr[i][j];
				int nc = j + arr[i][j];

				if (nr < N) {
					d[nr][j] += d[i][j];
				}
				if (nc < N) {
					d[i][nc] += d[i][j];
				}
			}
		}

		System.out.println(d[N - 1][N - 1]);

	} // end of main

}
