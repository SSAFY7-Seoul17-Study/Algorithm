package study.day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_2206_벽부수고이동하기_골4_추준성_584ms {
	/*
	 * - 최단거리 => BFS(가중치x), 다익스트라(가중치O) => BFS
	 * - 벽을 부술 수 있는지, 이미 부수었는지 상태 관리 => Node 클래스에 isBroken boolean 변수 추가
	 *   => 벽을 부수고 방문한 곳을 벽을 안 부순 놈이 방문 못하는 문제 발생 => visited를 3차원 배열로 상태관리 (벽을 부수고 방문한 경우, 벽을 부수지 않고 방문한 경우)
	 */
	static class Node {
		int r;
		int c;
		int dist;
		int isBroken; // 벽 부수면 0, 안 부쉈으면 1
		
		public Node(int r, int c, int dist, int isBroken) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.isBroken = isBroken;
		}
	}

	private static char[][] map;
	private static int N;
	private static int M;
	
	static int[] dr = {0, 0,-1, 1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.print(bfs());
		
	} // end of main

	static int bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		boolean[][][] visited = new boolean[N][M][2]; // index 0 - 벽 안 부수고 방문, index 1 - 벽 부수고 방문
		
		queue.add(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			
			Node cur = queue.poll();
			
			if(cur.r == N-1 && cur.c == M-1) return cur.dist; // 목적지에 도착하면 최단 거리 리턴
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc][cur.isBroken]) continue;
				
				if(map[nr][nc] == '1') { // 벽일 때
					if(cur.isBroken == 1) continue; // 벽을 이미 부순 적이 있으면 continue
					queue.add(new Node(nr, nc, cur.dist+1, 1)); // 벽을 부쉈으므로 isBroken을 1로 할당
				} else { // 벽이 아닐 때
					queue.add(new Node(nr, nc, cur.dist+1, cur.isBroken));
				}
				visited[nr][nc][cur.isBroken] = true;
			}
		}
		
		return -1; // 목적지에 도착 못한 경우 -1을 리턴
	}
	
} // end of class
