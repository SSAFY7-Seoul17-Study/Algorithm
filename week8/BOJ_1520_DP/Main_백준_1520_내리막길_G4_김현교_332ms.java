package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [개선점]
 * 방문한 노드 l,r에서 경로가 없을 때 0으로 dp에 저장되는데 초기값이 0이기 때문에
 * 이후 l,r에 방문해도 탐색을 또 진행하게 된다.
 * 
 * */

public class Main_백준_1520_내리막길_G4_김현교_332ms {

	public static void main(String[] args) throws IOException {
		Person sehjun = new Person();
		setInputMapData(sehjun);
		
		System.out.println(sehjun.searchPathOfDownhills());
	}//end main

	private static void setInputMapData(Person person) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] m = new int[M + 2][N + 2];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		person.setMap(new Map(M, N, m));
	}
	
	static class Person {
		private Map map;

		public Map getMap() {
			return map;
		}

		public void setMap(Map map) {
			this.map = map;
		}

		public int searchPathOfDownhills() {
			
			int[][] memo = new int[map.height + 2][map.width + 2];
			for (int i = 1; i <= map.height; i++)
				Arrays.fill(memo[i], -1);
			memo[map.height][map.width] = 1;
			
			boolean[][] visited = new boolean[map.height + 2][map.width + 2];
			visited[1][1] = true;
			
			return map.dfs(1, 1, visited, memo);
		}
	}
	
	static class Map {
		private int height, width;
		private int[][] map;
		private final int[] dl = {1, 0, -1, 0};
		private final int[] dr = {0, 1, 0, -1};
		
		public Map(int height, int width, int[][] map) {
			this.height = height;
			this.width = width;
			this.map = map;
		}

		public int dfs(int l, int r, boolean[][] visited, int[][] memo) {
			
			if (memo[l][r] >= 0)
				return memo[l][r];
			
			int cur = map[l][r];
			int ret = 0;
			for (int i = 0; i < 4; i++) {
				int nl = l + dl[i];
				int nr = r + dr[i];
				if (map[nl][nr] == 0 || visited[nl][nr] || cur <= map[nl][nr])
					continue;
				visited[nl][nr] = true;
				ret += dfs(nl, nr, visited, memo);
				visited[nl][nr] = false;
			}
			
			memo[l][r] = ret;
			return ret;
		}
	}
}//end class
/*
 * 
 * */
 
