package DynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_백준_2407_조합_S3_김현교_80ms {
	
	static BigInteger[][] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		dp = new BigInteger[n + 1][m + 1];
		System.out.println(combi(n, m).toString());
	}
	
	static BigInteger combi(int n, int m) {
		if (n == m || m == 0)
			return new BigInteger("1");
		
		if (dp[n][m] == null)
			dp[n][m] = combi(n - 1, m).add(combi(n - 1, m - 1));
		return dp[n][m];
	}
}//end class

/*
 * 입력: n과 m이 주어진다. (5 ≤ n ≤ 100, 5 ≤ m ≤ 100, m ≤ n)
 * 
 * nCm = n-1Cm + n-1Cm-1
 * 
 * Long타입도 오버플로우가 발생하므로 BigInteger타입을 이용한다.
 * 
 * */
