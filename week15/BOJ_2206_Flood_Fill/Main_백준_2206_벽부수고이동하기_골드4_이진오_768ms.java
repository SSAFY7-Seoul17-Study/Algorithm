import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_2206_벽부수고이동하기_골드4_이진오_768ms
 */
public class Main_백준_2206_벽부수고이동하기_골드4_이진오_768ms {
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c) - '0';
			}
		}
		
		// BFS
		boolean[][][] visited = new boolean[N][M][2];
		
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[0][0][0] = true;
		queue.offer(new int[] {0, 0, 0});
		
		int moves = 0;
		boolean hasReached = false;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			while (size-- > 0) {
				int[] state = queue.poll();
				
				int r = state[0];
				int c = state[1];
				int cnt = state[2];
				
				if (r == N-1 && c == M-1)
					hasReached = true;
				
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
						if (cnt == 0) {
							if (map[nr][nc] == 0) {
								if (!visited[nr][nc][0]) {
									visited[nr][nc][0] = true;
									queue.offer(new int[] {nr, nc, 0});
								}
							}
							else if (map[nr][nc] == 1 && !visited[nr][nc][1]) {
								visited[nr][nc][1] = true;
								queue.offer(new int[] {nr, nc, 1});
							}
						}
						else if (map[nr][nc] == 0 && !visited[nr][nc][1]) {
							visited[nr][nc][1] = true;
							queue.offer(new int[] {nr, nc, 1});
						}
					}
					
				} // end of for dr
			} // end of while size
			
			moves++;
			
			if (hasReached)
				break;
		} // end of while queue
		
		System.out.println(hasReached ? moves : -1);
		
	} // end of main
	
}