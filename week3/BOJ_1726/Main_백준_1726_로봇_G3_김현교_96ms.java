package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1726_로봇_G3_김현교_96ms {
	
	static int M, N;
	static char[][] m;
	static boolean[][][] v;
	
	static int[] dl = {0, 1, 0, -1};//동남서북
	static int[] dr = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		m = new char[M + 2][N + 2];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				m[i][j] = st.nextToken().charAt(0);
			}
		}
		st = new StringTokenizer(br.readLine());
		C start = new C(Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						getDirection(Integer.parseInt(st.nextToken())));
		
		st = new StringTokenizer(br.readLine());
		C end = new C(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					getDirection(Integer.parseInt(st.nextToken())));
		
		v = new boolean[M + 2][N + 2][4];
		Queue<C> q = new LinkedList<>();
		q.offer(start);
		v[start.l][start.r][start.dir] = true;
		while (!q.isEmpty()) {
			C cur = q.poll();
			
			if (cur.l == end.l && cur.r == end.r && cur.dir == end.dir) {
				System.out.println(cur.cmdCnt);
				return;
			}
			
			int cnt = cur.cmdCnt + 1;
			//직선거리 1 2 3
			for (int j = 1; j <= 3; j++) {
				int nl = cur.l + dl[cur.dir] * j;
				int nr = cur.r + dr[cur.dir] * j;
				if (nl < 1 || nl > M || nr < 1 || nr > N || m[nl][nr] != '0')
					break;
				if (v[nl][nr][cur.dir])
					continue;
				v[nl][nr][cur.dir] = true;
				q.offer(new C(nl, nr, cur.dir, cnt));
			}
			//회전 좌우
			for (int j = 0; j < 4; j++) {
				int rotateCnt = getCommandCount(j - cur.dir);
				if (rotateCnt != 1 || v[cur.l][cur.r][j])
					continue;
				v[cur.l][cur.r][j] = true;
				q.offer(new C(cur.l, cur.r, j, cnt));
			}
		}
	}//end main
	
	static int getDirection(int d) {
		switch (d) {
		case 1:
			return 0;
		case 2:
			return 2;
		case 3:
			return 1;
		case 4:
			return 3;
		}
		return 0;
	}
	
	static int getCommandCount(int diff) {
		switch (diff) {
		case 1: case -1: case 3: case -3:
			return 1;
		case 2: case -2:
			return 2;
		default:
			return 0;
		}
	}
	
	static class C {
		int l, r, dir, cmdCnt;

		public C(int l, int r, int dir) {
			this.l = l;
			this.r = r;
			this.dir = dir;
			cmdCnt = 0;
		}
		
		public C(int l, int r, int dir, int cmdCnt) {
			this.l = l;
			this.r = r;
			this.dir = dir;
			this.cmdCnt = cmdCnt;
		}
	}
}//end class
/*
 * DFS로 갈수 있는 경로를 찾음
 * 만약 찾아진 길이면 그 경로를 최소명령으로 수행해봄
 * 명령 횟수가 최소이면 갱신
 * 
 * 개선
 * 	-> dfs를 수행하며 방향이 바뀌는 경우는 명령어 + 1
 * 	-> 방향이 아예 반대인 경우는 + 2
 * 
 * 개선2
 * 	-> 생각해보니 최단거리로 도달하는 것이 무조건 가장 직선과 가까울 수 밖에 없음
 * 	-> 반대로 생각해보면 최단거리보다 회전을 덜 수행하면서(직선과가까우면서) 도착지에 도달할수는 없음
 * 	-> 따라서 그냥 BFS적으로 최단거리에 있는 지점의 명령어 개수만 세면 됨.
 * 
 * 개선3
 * 	-> BFS로 최단거리가 반드시 최소 명령어인 상황이 아님!!
 * 
 * 개선4
 *  -> 직선거리는 최대 3까지만 가능함.
 *  
 * 개선5
 *  -> 칸 이동이 아닌 cnt증가를 기준으로 BFS
10 10
0 1 1 1 0 0 0 0 0 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 1 0 0 1 1 1 1 1 1
0 0 0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0 0 1
0 0 0 1 1 0 0 0 1 1
0 1 0 1 1 1 1 0 1 1
0 0 0 1 1 1 1 1 1 1
1 1 1 1 1 1 1 0 1 1
1 9 1
9 1 3
 * */
