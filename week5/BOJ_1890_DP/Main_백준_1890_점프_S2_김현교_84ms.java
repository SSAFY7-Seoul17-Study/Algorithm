package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DFS + DP
 * 
 * 1. DFS로 하나의 경로를 끝까지 탐색한다.
 * 2. 그 경로가 도착지점까지 도착하면 1을 리턴한다.
 * 3. 그 도착지점으로 가는 DFS를 호출한 부분(A)에서 그 1을 받는다.
 * 4. A에서 다른 경로로 이동한 부분도 도착지점에 도달해서 도착 경우의 수를 리턴한다.
 * 5. A지점에서 호출된 모든 DFS의 경우의 수를 저장한 값(ret)을
 *    dp(A지점위치)에 저장하고, 그 값을 리턴해주어서
 * 	  A지점으로의 DFS를 호출한 지점(B)로 그 값을 전달해준다.(이 작업이 재귀적으로 반복)
 * 6. 만약 이후 다른 경로를 통해 A지점에 도달한다면,
 *    A지점에서 도착지점까지의 모든 경우의 수는 이미 dp에 저장되어있으므로
 *    그 값을 리턴해준다.
 * */
public class Main_백준_1890_점프_S2_김현교_84ms {

	static int N;
	static int[][] m;
	static long[][] dp;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new int[N][N];
		dp = new long[N][N];
		v = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		v[0][0] = true;
		System.out.println(dfs(0, 0));
	}//end main
	
	static long dfs(int l, int r) {
		
		long ret = dp[l][r];
		if (ret > 0)
			return ret;
		
		if (l == N - 1 && r == N - 1)
			return 1;
		
		int n = m[l][r];
		if (n == 0)
			return 0;
		
		if (l + n < N && !v[l + n][r]) {
			v[l + n][r] = true;
			ret += dfs(l + n, r);
			v[l + n][r] = false;
		}
		if (r + n < N && !v[l][r + n]) {
			v[l][r + n] = true;
			ret += dfs(l, r + n);
			v[l][r + n] = false;
		}
		dp[l][r] = ret;
		return ret;
	}
}//end class

