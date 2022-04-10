package study.day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1915_가장큰정사각형_골4_추준성_224ms {
	/*
	 * <설계>
	 * 1. dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1; 
	 * => 부분적으로 2x2 정사각형을 기준으로 판단하여 저장하는 코드 => 2x2 정사각형들이 가능 한 게 모여서 더 큰 정사각형을 이룰 수 있기 때문
	 * => (i, j) 기준으로 왼, 위, 왼위에 있는 값들 중, 최소인 값에 1을 더한 게 dp[i][j] 값이 되게 하고, 이를 통해 최댓값을 갱신해나가면 결과적으로 최대 정사각형의 변의 길이를 알 수 있음      
	 * 2. 부분 결과들의 종합이 전체를 결정 짓는다 -> dynamic programming
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 1x1 행렬로 주어지면, 1을 출력하고 끝냄
		if(N == 1 && M == 1) {
			System.out.print(1);
			return;
		}
		
		int maxLen = 0;
		int[][] dp = new int[N+1][M+1];
		
		// 이전 값들로 현재를 결정하는 dp 점화식이므로 입력과 동시에 dp연산 가능
		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++) {
				int temp = s.charAt(j-1) - '0'; // 입력 값 temp에 저장
				
				// dp[1][1]은 초기값으로 저장하고 시작
				if(i == 1 && j == 1) {
					dp[i][j] = temp;
					continue;
				}
				
				// 입력 값이 1인 경우에만 dp 점화식 실행하여 dp[i][j] 값 갱신
				if(temp == 1) {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
					maxLen = Math.max(maxLen, dp[i][j]); // dp[i][j] 값으로 최대 정사각형 한 변의 길이 갱신
				}
				
			}
		}
		
		System.out.print(maxLen * maxLen); // 최대 정사각형 넓이 출력
		
	} // end of main

} // end of class
