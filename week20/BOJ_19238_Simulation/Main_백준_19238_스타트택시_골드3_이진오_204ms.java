import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_19238_스타트택시_골드3_이진오_204ms
 * 
 *  - 이동 중간에 연료가 부족한 경우 실패 (목적지까지 이동 후에 다 소진한 경우 X)
 * 
 */
public class Main_백준_19238_스타트택시_골드3_이진오_204ms {
	
	static class Node {
		boolean canVisit;
		int customer;
		boolean[] destination;
		
		public Node() {
			destination = new boolean[M+1];
		}
	}
	
	static int N, M;
	static int fuel;
	
	static Node[][] map;
	static boolean[][] visited;
	
	static int[] taxi; // {taxiR, taxiC}
	static int[] customer; // {customerNo, customerR, customerC}
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N <= 20
		M = Integer.parseInt(st.nextToken()); // M <= N^2
		fuel = Integer.parseInt(st.nextToken()); // fuel <= 500,000
		
		map = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node();
			}
		}
		
		visited = new boolean[N][N];
		taxi = new int[2];
		customer = new int[3];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				switch (line.charAt(j<<1)) {
				case '0':
					map[i][j].canVisit = true;
					break;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		taxi[0] = Integer.parseInt(st.nextToken())-1;
		taxi[1] = Integer.parseInt(st.nextToken())-1;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int customerR = Integer.parseInt(st.nextToken())-1;
			int customerC = Integer.parseInt(st.nextToken())-1;
			
			int destinationR = Integer.parseInt(st.nextToken())-1;
			int destinationC = Integer.parseInt(st.nextToken())-1;
			
			map[customerR][customerC].customer = i;
			map[destinationR][destinationC].destination[i] = true;
		}
		
		boolean successfulDay = true;
		for (int i = 1; i <= M; i++) {
			if (!bfs()) {
				successfulDay = false;
				break;
			}
		}
		
		if (successfulDay)
			System.out.println(fuel);
		else
			System.out.println(-1);
	}
	
	public static boolean bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[taxi[0]][taxi[1]] = true;
		queue.offer(new int[] {taxi[0], taxi[1]});
		
		int dist = 0;
		
		customer[0] = 0;
		
		while (!queue.isEmpty()) {
			
			int size = queue.size();
			
			while (size-- > 0) {
				int[] coord = queue.poll();
				int r = coord[0];
				int c = coord[1];
				
				if (map[r][c].customer > 0) { // 손님이 있을 때
					if (customer[0] == 0) {
						customer[0] = map[r][c].customer;
						customer[1] = r;
						customer[2] = c;
					}
					else if (r < customer[1]) {
						customer[0] = map[r][c].customer;
						customer[1] = r;
						customer[2] = c;
					}
					else if (r == customer[1] && c < customer[2]) {
						customer[0] = map[r][c].customer;
//						customer[1] = r;
						customer[2] = c;
					}
				}
				
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || !map[nr][nc].canVisit)
						continue;

					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			if (customer[0] > 0)
				break;
			
			dist++;
		}
		
		if (customer[0] == 0 || fuel < dist) // 손님을 못 찾았을 경우, 연료가 부족한 경우
			return false;
		
		fuel -= dist;
		taxi[0] = customer[1];
		taxi[1] = customer[2];
		map[taxi[0]][taxi[1]].customer = 0;
		
		queue.clear();
		clear();
		
		queue.offer(new int[] {taxi[0], taxi[1]});
		visited[taxi[0]][taxi[1]] = true;
		
		dist = 0;
		
		boolean hasReached = false;
		while (!queue.isEmpty()) {
			
			int size = queue.size();
			
			while (size-- > 0) {
				int[] coord = queue.poll();
				int r = coord[0];
				int c = coord[1];
				
				if (map[r][c].destination[customer[0]]) {
					taxi[0] = r;
					taxi[1] = c;
					hasReached = true;
					break;
				}
				
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || !map[nr][nc].canVisit)
						continue;

					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			if (hasReached)
				break;
			
			dist++;
		}
		
		if (!hasReached || fuel < dist)
			return false;
		
		fuel += dist;
		clear();
		
		return true;
	}
	
	public static void clear() {
		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);
	}
}
