package baekjoon.i;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Main_백준_1890_점프_실버2_이진오_76ms
 * 
 *  - 아이디어(dynamic programming) => 경우의 수 카운팅
 *  
 *    1) 점화식 정의
 *      : f(r, c) = (r, c)에서 (N-1, N-1)까지 가능한 경로의 수
 *    2) 점화식 초기 조건
 *      : f(N-1, N-1) = 1
 *      : if (map[r][c] == 0) 일 경우, f(r, c) = 0
 *    3) 점화식 간 관계 규정
 *      : f(r, c) = f(r + map[r][c], c) + f(r, c + map[r][c])
 * 
 */
public class Main_백준_1890_점프_실버2_이진오_76ms {
	static int N;
	static char[][] map;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		dp = new long[N][N];
		
		// dp 배열 초기화
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		// dp 중 초기 조건, 잡고 들어갈 수 있다. 
		dp[N-1][N-1] = 1;
		
		for (int i = 0; i < map.length; i++) {
			String line = in.readLine();
			for (int j = 0; j < map[i].length; j++) {
				char c = line.charAt(j<<1);
				map[i][j] = c;
			}
		}
		
		// (0, 0)에서부터 탐색한다. 
		System.out.println(search(0, 0));
		
	} // end of main
	
	public static long search(int r, int c) {
		// f(r, c)가 이미 정해진 값이라면 - 다시 탐색하지 않는다. 
		if (dp[r][c] >= 0)
			return dp[r][c];
		
		int dist = map[r][c] - '0';
		
		// 더 이상 탐색할 수 없는 경우
		if (dist == 0) {
			dp[r][c] = 0;
			return 0;
		}
		
		// 반환 값은, int 타입의 값의 범위를 넘어선다. 
		long ret = 0;
		
		// 오른쪽, 아래로 이동
		if (r + dist < N)
			ret += search(r + dist, c);
		if (c + dist < N)
			ret += search(r, c + dist);
		
		dp[r][c] = ret;
		return dp[r][c];
	}
	
} // end of class
