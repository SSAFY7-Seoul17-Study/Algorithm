package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1915_가장큰정사각형_G4_김현교_220ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n + 1][m + 1];
		int max = 0;
		
		for (int i = 1; i <= n; i++) {
			String line = br.readLine();
			for (int j = 1; j <= m; j++) {
				if (line.charAt(j - 1) == '1') {
					int over = dp[i - 1][j];
					int left = dp[i][j - 1];
					int diag = dp[i - 1][j - 1];
					
					if (over == 0 || left == 0 || diag == 0)
						dp[i][j] = 1;
					else
						dp[i][j] = Math.min(over, Math.min(left, diag)) + 1;
					
					if (dp[i][j] > max)
						max = dp[i][j];
				}
			}
		}
		
		System.out.println(max * max);
	}
}
