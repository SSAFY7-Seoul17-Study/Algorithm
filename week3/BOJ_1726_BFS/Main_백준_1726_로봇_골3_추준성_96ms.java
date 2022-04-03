package study.day4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_1726_로봇_골3_추준성_96ms {
	/*
	 * 1. bfs 탐색
	 * 2. 로봇의 좌표 뿐만 아니라 방향정보와 명령횟수(카운트)도 함께 담음 (방향정보도 방문체크 배열의 정보로 포함)
	 * 3. 현재 이동방향 & 현재 이동 방향의 좌우 방향 고려
	 *  1) 현재 방향과 동일한 방향이라면 - 최대 3번 이동한 후 정보 큐에 담기 (방향 정보 유지)
	 *  2) 현재 방향의 좌우 방향 이라면 - 방향 정보 갱신 후 큐에 담기 (위치 정보 유지)
	 */
	private static int[] dr = {0,  0, 1,-1}; // 동서남북
	private static int[] dc = {1, -1, 0, 0};
	private static int M;
	private static int N;
	private static int command;
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M+1][N+1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] start = new int[4]; // 로봇 시작 지점, 방향, 명령 횟수
		int[] end = new int[4]; // 로봇 끝나는 지점, 방향, 명령 횟수
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {
			start[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {
			end[i] = Integer.parseInt(st.nextToken());
		}
		
		bfs(start, end);
		
		System.out.println(command);
	} // end of main
	
	public static void bfs(int[] start, int[] end) {
		LinkedList<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[M+1][N+1][5];
		queue.add(start);
		visited[start[0]][start[1]][start[2]] = true;
		
		// bfs 탐색
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int r = cur[0];
			int c = cur[1];
			int dir = cur[2]; // 1234
			int cnt = cur[3];
			
			// 현재 위치에서 end 정보를 모두 만족한다면 현재까지 명령 횟수 반환하고 탐색 종료
			if(r == end[0] && c == end[1] && dir == end[2]) {
				command = cnt;
				return;
			}
			
			// case 1. 현재 로봇이 향하는 방향으로 움직이면 최대 3번 이동
			for (int i = 1; i <= 3; i++) {
				int nr = r + (dr[dir-1] * i);
				int nc = c + (dc[dir-1] * i);
				
				if(nr <= 0 || nc <= 0 || nr > M || nc > N) continue; // map 경계 체크 (예외처리)
				
				
				// 다음 이동 방향이 1이면 for문 탈출(연속적으로 움직일 수 없으므로)
				if(map[nr][nc] == 1) break; // 예외처리
				
				// 방문한 곳이면 continue(지나갈 수는 있음), 방문하지 않은 곳이면 queue에 담기
				if(visited[nr][nc][dir]) continue; // 예외처리
				else {
					visited[nr][nc][dir] = true; // 방문 체크
					queue.add(new int[] {nr, nc, dir, cnt+1});
				}
			}
			
			// case 2. 현재 로봇 위치에서 방향만 좌우로 틀기
			
			// (1) 현재 이동 방향에서의 좌우 방향 인덱스 구하기
			int right = 0, left = 0;
			switch(dir) {
				case 1: left = 4; right = 3; break; // 동
				case 2: left = 3; right = 4; break; // 서
				case 3: left = 1; right = 2; break; // 남
				case 4: left = 2; right = 1; break; // 북
			}
			
			// (2) 현재 위치에서 방향만 좌우로 틀었을 때, 해당 방향으로 현재 위치를 방문하지 않았다면 의 좌표값을 큐에 담기
			if(!visited[r][c][left]) {
				visited[r][c][left] = true;
				queue.add(new int[] {r, c, left, cnt+1});
			}
			if(!visited[r][c][right]) {
				visited[r][c][right] = true;
				queue.add(new int[] {r, c, right, cnt+1});
			}

		}
	} // end of method bfs
	
} // end of class
