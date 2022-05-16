package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1932_정수삼각형_s1_228ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] tri = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
			}
		}
		
		System.out.println(tri[0][0]);
	}//end main
}
/** 
 * 
 * 아래쪽에서부터 먼저 선택하기!
 * n-1라인부터 시작
 * 왼쪽아래, 오른쪽 아래 중 큰것과 더하기
 * 
 * */
