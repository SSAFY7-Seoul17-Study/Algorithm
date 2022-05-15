import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_1865_웜홀_골드3_이진오_352ms
 * 
 *  - 벨만 포드 (최단 경로)
 *  
 */
public class Main_백준_1865_웜홀_골드3_이진오_352ms {
	
	private static final int MAX = 7654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		int TC = Integer.parseInt(in.readLine());
		
		// 자료 구조 선언, 초기화
		ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
		
		adjList.add(null);
		for (int i = 0; i < 500; i++) {
			adjList.add(new ArrayList<int[]>());
		}
		
		int[] upper = new int[501];
		
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 정점의 개수, N <= 500
			int M = Integer.parseInt(st.nextToken()); // 도로의 개수, M <= 2500 | 무향 / 양의 간선
			int W = Integer.parseInt(st.nextToken()); // 웜홀의 개수, W <= 200  | 유향 / 음의 간선
			
			for (int i = 0; i < M; i++) { // 도로 입력
				st = new StringTokenizer(in.readLine());
				
				int S = Integer.parseInt(st.nextToken()); // 시작 정점
				int E = Integer.parseInt(st.nextToken()); // 도착 정점
				int T = Integer.parseInt(st.nextToken()); // 간선의 가중치
				
				adjList.get(S).add(new int[] {E, T});
				adjList.get(E).add(new int[] {S, T});
			}
			
			for (int i = 0; i < W; i++) { // 웜홀 입력
				st = new StringTokenizer(in.readLine());
				
				int S = Integer.parseInt(st.nextToken()); // 시작 정점
				int E = Integer.parseInt(st.nextToken()); // 도착 정점
				int T = Integer.parseInt(st.nextToken()); // 간선의 가중치
				
				adjList.get(S).add(new int[] {E, -T});
			}
			
			out.append(bellmanFord(N, adjList, upper)? "YES": "NO").append("\n");
			init(N, adjList);
		}
		
		System.out.print(out.toString());
	}
	
	/**
	 * @param N
	 * @param adjList
	 * @param upper
	 * @return 음의 사이클의 존재하는 경우 true, 아닐 경우 false
	 * 
	 * 0) 초기화
	 *   : upper[start] = 0;
	 * 
	 * 1) 정점의 개수-1 만큼 반복
	 *   if upper[v] + w(v, u) < upper[u]
	 *     then upper[u] = upper[v] + w(v, u)
	 *   
	 * 2) 음의 사이클 존재 여부 확인
	 *   update가 일어난다면, 음의 사이클이 존재하는 것을 확정
	 */
	public static boolean bellmanFord(int N, ArrayList<ArrayList<int[]>> adjList, int[] upper) {
		Arrays.fill(upper, MAX);
		
		upper[1] = 0;
		boolean updated = false;
		
		for (int i = 0; i < N; i++) {
			updated = false;
			
			for (int v = 1; v <= N; v++) {
				int d = upper[v];
				
				for (int[] edge : adjList.get(v)) {
					int to = edge[0];
					int weight = edge[1];
					
					if (d + weight < upper[to]) {
						upper[to] = d + weight;
						updated = true;
					}
				}
			}
			
			if (!updated)
				break;
		}
		
		if (updated)
			return true;
		return false;
	}

	public static void init(int N, ArrayList<ArrayList<int[]>> adjList) {
		for (int i = 1; i <= N; i++) {
			adjList.get(i).clear();
		}
	}
}
