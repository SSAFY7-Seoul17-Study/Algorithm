import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_1944_복제로봇_골드2_332ms
 * 
 *  - 아이디어
 *    : 현재 로봇의 집합 {S, K1, ..., K2}
 *    : 에서 가능한 가장 짧은 경로로 도달 가능한 다음 K를 로봇의 집합에 추가한다. 
 * 
 */
public class Main_백준_1944_복제로봇_골드2_이진오_332ms {
	
	static class Key implements Comparable<Key> {
		int r, c;
		int dist;
		public Key(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int compareTo(Key o) {
			return this.dist - o.dist;
		}
	} // end of class Key
	
	static int N; 
	static int M; 
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	static int[][] dist;
	static char[][] map;
	static PriorityQueue<Key> pQueue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 미로의 크기, 4 <= N <= 50
		M = Integer.parseInt(st.nextToken()); // 열쇠의 개수, 1 <= M <= 250
		dist = new int[N][N]; // K의 최소 거리 저장용 배열
		map = new char[N][];  // 입력받은 map
		
		int sr = 0;
		int sc = 0;
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < N; c++) {
				// 시작 좌표를 구한다. 
				if (map[r][c] == 'S') {
					sr = r;
					sc = c;
				}
				// map이 K일 경우, dist 배열의 값을 MAX로 초기화
				else if (map[r][c] == 'K') {
					dist[r][c] = Integer.MAX_VALUE;
				}
			}
		}
		
		// 현재 분열 가능한 로봇의 집합에서 가장 짧은 거리로 방문할 수 있는 Key를 위한 PQ
		pQueue = new PriorityQueue<Key>();
		// 최소 거리가 확정된 Key를 확인하기 위한 배열
		boolean[][] confirmed = new boolean[N][N];
		boolean[][] visited = new boolean[N][N];
		
		pQueue.offer(new Key(sr, sc, 0));
		
		Key present = null;
		while(!pQueue.isEmpty()) {
			present = pQueue.poll();
			if (confirmed[present.r][present.c]) continue;
			
			dist[present.r][present.c] = present.dist;
			confirmed[present.r][present.c] = true;
			
			bfsAll(present.r, present.c, confirmed, visited);
			clear(visited);
		} // end of while pQueue
		
		int cnt = 0;
		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (confirmed[r][c]) {
					cnt++;
					sum += dist[r][c];
				}
			}
		}
		if (cnt <= M) sum = -1;
		
		System.out.println(sum);
		
	} // end of main
	
	public static void clear(boolean[][] visited) {
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
	} // end of clear
	
	public static void bfsAll(int sr, int sc, boolean[][] confirmed, boolean[][] visited) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int level = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-- > 0) {
				int[] temp = queue.poll();
				int r = temp[0];
				int c = temp[1];
				
				if (!confirmed[r][c] && map[r][c] == 'K' && level < dist[r][c]) {
					pQueue.offer(new Key(r, c, level));
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr > 0 && nc > 0 && nr < N && nc < N && map[r][c] != '1' &&
							!confirmed[nr][nc] && !visited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
			level++;
		} // end of while queue
		
	} // end of bfsAll
	
} // end of class