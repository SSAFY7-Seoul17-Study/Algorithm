package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * PQ를 사용하면 오히려 이전 cnt를 전부 탐색하므로 비효율이 나오게 됨.
 * 
 * */

public class Main_백준_2206_벽부수고이동하기_G4_김현교_544ms {

	static int[] dl = {1, 0, -1, 0};
	static int[] dr = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] m = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				m[i][j] = line.charAt(j);
			}
		}
		
		boolean[][][] v = new boolean[2][N][M];
		v[0][0][0] = true;
		Queue<C> q = new LinkedList<>();
		q.offer(new C(0, 0, 1));
		
		while (!q.isEmpty()) {
			C cur = q.poll();
			
			if (cur.l == N - 1 && cur.r == M - 1) {
				System.out.println(cur.cnt);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nl = cur.l + dl[i];
				int nr = cur.r + dr[i];
				if (nl < 0 || nl >= N || nr < 0 || nr >= M)
					continue;
				
				if (m[nl][nr] == '1') {
					if (!cur.isWall && !v[1][nl][nr]) {
						v[1][nl][nr] = true;
						q.offer(new C(nl, nr, cur.cnt + 1, true));
					}
					continue;
				}
				
				int idx = cur.isWall ? 1 : 0;
				if (!v[idx][nl][nr]) {
					v[idx][nl][nr] = true;
					q.offer(new C(nl, nr, cur.cnt + 1, cur.isWall));
				}
			}
		}
		System.out.println(-1);
	}//end main
	
	static class C {
		int l, r, cnt;
		boolean isWall;

		public C(int l, int r, int cnt) {
			this.l = l;
			this.r = r;
			this.cnt = cnt;
		}
		
		public C(int l, int r, int cnt, boolean isWall) {
			this.l = l;
			this.r = r;
			this.cnt = cnt;
			this.isWall = isWall;
		}
	}
}//end class

