package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2096_내려가기_G4_김현교_348ms {

	static final int MAX = 0, MIN = 1;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][][] memo = new int[2][N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				memo[MAX][i][j] = Integer.parseInt(st.nextToken());
				memo[MIN][i][j] = memo[MAX][i][j];
			}
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int left = j - 1 < 0 ? -1 : memo[MAX][i - 1][j - 1];
				int mid = memo[MAX][i - 1][j];
				int right = j + 1 >= 3 ? -1 : memo[MAX][i - 1][j + 1];
				memo[MAX][i][j] += Math.max(left, Math.max(mid, right));
				
				left = j - 1 < 0 ? Integer.MAX_VALUE : memo[MIN][i - 1][j - 1];
				mid = memo[MIN][i - 1][j];
				right = j + 1 >= 3 ? Integer.MAX_VALUE : memo[MIN][i - 1][j + 1];
				memo[MIN][i][j] += Math.min(left, Math.min(mid, right));
			}
		}
		
		int max = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (max < memo[MAX][N - 1][i])
				max = memo[MAX][N - 1][i];
			if (min > memo[MIN][N - 1][i])
				min = memo[MIN][N - 1][i];
		}
		
		System.out.println(max + " " + min);
	}//end main
}//end class
/* 
 * 
 * N * 3 * 2인 memo배열 생성
 * 
 * 1번째 줄부터 계산 수행
 * 
 * 최대값, 최소값 각각저장
 * 
 * */
