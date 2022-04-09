package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11066_파일합치기_G3_김희영_812ms {

	/**
	 * dp, memoization
	 * 점화식
	 * dp[i][j] = i부터 j장까지 합치는 비용
	 * 1개 : dp[i][i] = novel[i]
	 * 2개 : dp[i][i + 1] = novel[i] + novel[i+1]
	 * 3개 : dp[i][i + 2] = dp[i][i] + dp[i+1][i+2] + (novel[i]  + novel[i + 1] + novel[i + 2])
	 *					   dp[i][i+1] + dp[i + 2][i + 2] + (novel[i] + novel[i + 1] + novel[i + 2])
	 *				   	     중 작은 값
	 * 결론
	 * dp[i][j] = dp[i][i + divide] + dp[divide + 1][j] + sum(i부터 j까지 부분합)
	 * 			  divide 범위 : i + 1 ~ j - 1
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int testCase = 0; testCase < TC; testCase++) {

			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N + 1];
			int[][] memo = new int[N + 1][N + 1];
			int[] sum = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + arr[i];
			}

			for (int n = 1; n <= N; n++) {
				for (int from = 1; from + n <= N; from++) {
					int to = from + n;
					memo[from][to] = Integer.MAX_VALUE;
					for (int divide = from; divide < to; divide++) {
						memo[from][to] = Math.min(memo[from][to], memo[from][divide] + memo[divide + 1][to]);
					}
					memo[from][to] += sum[to] - sum[from - 1];
				}
			}
			sb.append(memo[1][N]).append("\n");
		}

		System.out.println(sb.toString());

	} // end of main

} // end of class
