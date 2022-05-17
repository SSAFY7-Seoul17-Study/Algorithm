package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

 *         0
 *       0   1
 *     0   1   2
 *   0   1   2   3
 * 0   1   2   3   4

 * 
 * 현재 계산하는 칸에 오기까지 max를 저장하는 memo 배열
 * memo[i][j] = max( memo[i - 1][j - 1] + arr[i][j], memo[i - 1][j] + arr[i][j] )
 * 
 * 그러나 계산의 편의를 위해 memo[i - 1][j - 1]의 관점에서
 * memo[i][j]와 memo[i][j + 1]을 갱신하는 방향으로 작성
 */
public class Main_BOJ_1932_정수삼각형_S1_김희영_232ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < i + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] memo = new int[N][N];
		memo[0][0] = arr[0][0];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				int tmp = memo[i - 1][j] + arr[i][j];
				memo[i][j] = Math.max(memo[i][j], tmp);

				tmp = memo[i - 1][j] + arr[i][j + 1];
				memo[i][j + 1] = Math.max(memo[i][j + 1], tmp);
			}
		}

		int max = memo[N - 1][0];
		for (int i = 1; i < N; i++) {
			if (memo[N - 1][i] > max)
				max = memo[N - 1][i];
		}

		System.out.println(max);
	} // end of main

} // end of class
