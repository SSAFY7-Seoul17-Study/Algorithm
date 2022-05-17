package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 현재 계산하는 칸에 오기까지 max와 min을 저장하는 ans 배열
 */
public class Main_BOJ_2096_내려가기_G4_김희영_252ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < 3; j++, index += 2) {
				arr[i][j] = str.charAt(index) - '0';
			}
		}
		int[][][] ans = new int[N][3][2]; // max, min
		for (int i = 0; i < 3; i++) {	// 초기화
			ans[0][i][0] = arr[0][i];
			ans[0][i][1] = arr[0][i];
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < 3; j++) {
				int t_max = ans[i][1][0]; // 가운데 값을 기준으로 생각
				int t_min = ans[i][1][1];

				if (j != 2) { // j가 0, 1인 경우
					t_max = Math.max(t_max, ans[i][0][0]);
					t_min = Math.min(t_min, ans[i][0][1]);
				}
				if (j != 0) { // j가 1, 2인 경우
					t_max = Math.max(t_max, ans[i][2][0]);
					t_min = Math.min(t_min, ans[i][2][1]);
				}
				ans[i + 1][j][0] = arr[i + 1][j] + t_max;
				ans[i + 1][j][1] = arr[i + 1][j] + t_min;
			}
		}

		int max = Math.max(Math.max(ans[N - 1][0][0], ans[N - 1][1][0]), ans[N - 1][2][0]);
		int min = Math.min(Math.min(ans[N - 1][0][1], ans[N - 1][1][1]), ans[N - 1][2][1]);
		System.out.println(max + " " + min);

	} // end of main

} // end of class
