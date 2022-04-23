import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_17142_연구소3_골드4_이진오_228ms
 * 
 *  - 시뮬레이션
 *    1) 조합
 *      : 전체 바이러스의 좌표에서, M개의 좌표를 GET
 *      
 *    2) BFS
 *      : 맵 전체를 순회하는 BFS코드 작성
 *        => 이 때, 빈 칸 전체가 채워졌다면 BFS 종료
 *        
 *  - 최적화 코드
 *    : 하나의 바이러스에 대해 BFS를 하며, 각 좌표까지 걸리는 시간을 구함
 *    : M개의 조합을 이룬 이후, 이전에 계산한 좌표까지 걸리는 시간을 통해 최소 시간 계산
 *    
 * 
 */
public class Main_백준_17142_연구소3_골드4_이진오_228ms {
	
	private static int[] dr = {0, 0, 1,-1};
	private static int[] dc = {1,-1, 0, 0};
	
	private static int numEmpty;
	private static int[][] map;
	private static int[][] virusContainer;
	private static ArrayList<int[]> virusList;
	private static Queue<int[]> queue;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // N <= 50
		int M = Integer.parseInt(st.nextToken()); // M <= 10
		
		map = new int[N][N];
		virusContainer = new int[M][];
		virusList = new ArrayList<int[]>();
		queue = new LinkedList<int[]>();
		visited = new boolean[N][N];
		
		numEmpty = 0;
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < map[i].length; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				map[i][j] = temp;
				if (temp == 0) {
					numEmpty++;					
				}
				else if (temp == 2) {
					virusList.add(new int[] {i, j});
				}
			}
		}
		
		System.out.println(combination(0, 0, M, virusContainer));
	}
	
	public static int combination(int cnt, int start, int M, int[][] container) {
		if (cnt == M) {
			return bfs(container);
		}
		
		int ret = 10000;
		
		for (int i = start; i < virusList.size(); i++) {
			container[cnt] = virusList.get(i);
			int temp = combination(cnt+1, i+1, M, container);
			if (temp >= 0 && temp < ret)
				ret = temp;
		}
		
		if (ret == 10000)
			return -1;
		return ret;
	}
	
	public static int bfs(int[][] container) {
		int N = map.length;
		
		int time = 0;
		int cntEmpty = 0;
		
		queue.clear();
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		
		// 큐 초기화
		for (int i = 0; i < container.length; i++) {
			queue.offer(container[i]);
			visited[container[i][0]][container[i][1]] = true;
		}
		
		while (!queue.isEmpty()) {
			if (cntEmpty == numEmpty)
				break;
			
			int size = queue.size();
			
			while (size-- > 0) {
				int[] temp = queue.poll();
				int r = temp[0];
				int c = temp[1];
				
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && 
							map[nr][nc] != 1 && !visited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
						if (map[nr][nc] == 0)
							cntEmpty++;
					}
				}
			}
			
			time++;
		}
		
		if (cntEmpty == numEmpty)
			return time;
		
		return -1;
	}
}
