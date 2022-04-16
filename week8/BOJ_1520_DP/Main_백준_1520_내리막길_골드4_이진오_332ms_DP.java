import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_1520_내리막길_골드4_이진오_332ms_DP
 * 
 *  - DP 하향식
 * 
 * f(r, c) = 
 *   1) if map[r+1][c] < map[r][c] then += f(r+1, c)
 *   2) if map[r][c+1] < map[r][c] then += f(r, c+1)
 *   3) if map[r-1][c] < map[r][c] then += f(r-1, c)
 *   4) if map[r][c-1] < map[r][c] then += f(r, c-1)
 * 
 */
public class Main_백준_1520_내리막길_골드4_이진오_332ms_DP {
	
	private static int[][] map;
	private static int[][] memo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken()); // 세로의 크기, M <= 500
		int N = Integer.parseInt(st.nextToken()); // 가로의 크기, N <= 500
		
		map = new int[M][N];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		memo = new int[M][N];
		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}
		memo[M-1][N-1] = 1;
		
		System.out.println(dfs(0, 0));
	}
	
	private static int[] dr = {0, 0, 1,-1};
	private static int[] dc = {1,-1, 0, 0};
	
	public static int dfs(int r, int c) {
		if (memo[r][c] >= 0)
			return memo[r][c];
		
		int ret = 0;
		
		int val = map[r][c];
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < map.length && nc < map[0].length && 
					val > map[nr][nc]) {
				ret += dfs(nr, nc);
			}
		}
		
		memo[r][c] = ret;
		return memo[r][c];
	}
}
