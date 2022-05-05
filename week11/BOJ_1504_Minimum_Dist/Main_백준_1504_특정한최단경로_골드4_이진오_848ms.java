import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_백준_1504_특정한최단경로_골드4_이진오_848ms
 * 
 *  - 문제
 *    :특정 정점을 무조건 지나는 최단 경로를 탐색
 *  
 *  - 해석
 *    : N <= 800, E <= 200,000
 *      => 인접 행렬 사용
 *    : 특정 정점을 지나는 최단 경로
 *      1) 다익스트라
 *      2) 플로이드 워셜
 *    
 * 
 */
public class Main_백준_1504_특정한최단경로_골드4_이진오_848ms {
	
	final static int MAX = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] adjMat = new int[N+1][N+1];
		
		for (int i = 0; i < adjMat.length; i++) {
			Arrays.fill(adjMat[i], MAX);
			adjMat[i][i] = 0;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = adjMat[a][b];
			
			if (c < d) {
				adjMat[a][b] = c;
				adjMat[b][a] = c;
			}
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int[] dist1 = dijkstra(1, N, adjMat);
		int[] distV1 = dijkstra(v1, N, adjMat);
		int[] distV2 = dijkstra(v2, N, adjMat);
		
		int min = MAX;
		if (dist1[v1] < MAX && distV1[v2] < MAX && distV2[N] < MAX) {
			min = dist1[v1] + distV1[v2] + distV2[N];
		}
		if (dist1[v2] < MAX && distV2[v1] < MAX && distV1[N] < MAX) {
			min = Math.min(min, dist1[v2] + distV2[v1] + distV1[N]);
		}
		if (min >= MAX)
			min = -1;
		
		System.out.println(min);
	}
	
	public static int[] dijkstra(int start, int N, int[][] adjMat) {
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		// int[] = {v, d} | start ~ v 까지 거리가 d인 경로를 저장
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(
				new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						return o1[1] - o2[1];
					}
				});
		
		// dist 배열 초기화
		Arrays.fill(dist, MAX);
		// pQueue 초기화
		pQueue.offer(new int[] {start, 0});
		
		while (!pQueue.isEmpty()) {
			int[] edge = pQueue.poll();
			
			int v = edge[0];
			int d = edge[1];
			
			if (visited[v])
				continue;
			
			dist[v] = d;
			visited[v] = true;
			
			for (int i = 1; i < adjMat[v].length; i++) {
				if (visited[i] || adjMat[v][i] >= MAX)
					continue;
				
				if (d + adjMat[v][i] < dist[i]) {
					pQueue.offer(new int[] {i, d + adjMat[v][i]});
				}
			}
		}
		
//		System.out.println(Arrays.toString(dist));
		
		return dist;
	}
}
