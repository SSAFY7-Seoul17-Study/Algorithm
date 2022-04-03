import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Main_백준_9252_LCS2_골드4_이진오_236ms
 * 
 *  - 입력
 * 	  : 문자열 집합 a, b (글자 수 <= 1000) 
 * 	
 *  - 아이디어(dp)
 *    : dp[ai][bi] = 
 *        if a[ai] equals b[bi] then dp[ai+1][bi+1] + 1
 *        
 *        else if dp[ai+1][bi] > dp[ai][bi+1] then dp[ai+1][bi]
 *        else dp[ai][bi+1]
 * 
 */
public class Main_백준_9252_LCS2_골드4_이진오_236ms {
	
	/**	문자열 집합 a, b */
	private static char[] a;
	private static char[] b;
	/**	dp[i][j] = a[i...] 와 b[j...]의 LCS */
	private static int[][] dp;
	/**	next[i][j] = (i, j)와 연결된 다음 좌표 */
	private static int[][][] next;
	/**	selected[i][j] = (a_i == b_j)라면, selected[i][j] = true */
	private static boolean[][] selected;

	public static int f(int ai, int bi) {
		// base case
		if (ai >= a.length || bi >= b.length)
			return 0;
		if (dp[ai][bi] >= 0)
			return dp[ai][bi];
		
		// a[ai]와 b[bi]가 LCS의 하나의 원소로 가능하다면, 무조건 선택한다. 
		if (a[ai] == b[bi]) {
			selected[ai][bi] = true;
			next[ai][bi][0] = ai+1;
			next[ai][bi][1] = bi+1;
			dp[ai][bi] = 1 + f(ai+1, bi+1);
			return dp[ai][bi];
		}
		
		// 두 경로를 탐색한다.  
		int a = f(ai+1, bi);
		int b = f(ai, bi+1);
		
		// a의 인덱스를 1 증가시키는 것이 더 낫다면, 선택한다. 
		if (a > b) {
			next[ai][bi][0] = ai+1;
			next[ai][bi][1] = bi;
		} 
		// b의 인덱스를 1 증가시키는 것이 더 낫다면, 선택한다. 
		else {
			next[ai][bi][0] = ai;
			next[ai][bi][1] = bi+1;
		}
		
		dp[ai][bi] = Math.max(a, b);
		return dp[ai][bi];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		a = in.readLine().toCharArray();
		b = in.readLine().toCharArray();
		
		// 1 <= n, m <= 1,000
		int n = a.length;
		int m = b.length;
		
		// 동적 계획법에 사용될 자료 구조 초기화
		dp = new int[n][m];
		next = new int[n][m][2];
		selected = new boolean[n][m];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		// a와 b의 LCS의 길이
		int answer = f(0, 0);
		System.out.println(answer);
		
		// dp의 경로를 탐색하며, 선택된 문자를 StringBuilder에 append
		if (answer > 0) {
			int ai = 0;
			int bi = 0;
			
			StringBuilder sb = new StringBuilder();
			while (ai < n && bi < m) {
				if (selected[ai][bi])
					sb.append(a[ai]);
				
				int[] coord = next[ai][bi];
				ai = coord[0];
				bi = coord[1];
			}
			System.out.println(sb.toString());
		}
		
	} // end of main
	
} // end of class
