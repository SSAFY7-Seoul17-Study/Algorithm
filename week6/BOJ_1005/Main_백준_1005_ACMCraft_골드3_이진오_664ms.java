import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_1005_ACMCraft_골드3_이진오_664ms
 * 
 *  - 아이디어
 *    : 건물의 건설 순서 X Y가 주어진다. => Directional Graph 
 *    : 건설 순서는 모든 건물이 건설 가능하도록 주어진다. => Acyclic Graph
 *    : 건물 X가 지어져야 건물 Y를 지을 수 있다 => Y -> X
 *  
 *  - DAG(Directional Acyclic Graph)
 *    : 방향성을 가지며, 사이클이 존재하지 않는 그래프
 *    : 위상 정렬이 가능하다
 *  
 *  - 위상 정렬(Topological sort)
 *    : DAG의 각 정점들을, 시작 정점으로부터의 거리에 따라 정렬
 * 
 */
public class Main_백준_1005_ACMCraft_골드3_이진오_664ms {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		int[] drr = new int[1001];
		boolean[] visited = new boolean[1001];
		ArrayList<Integer>[] adjList = new ArrayList[1001];
		
		for (int testCase = 0; testCase < T; testCase++) {
			/* -------------- 입력 처리 시작 -------------- */
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken()); // 2 <= N <= 1,000 (건물 번호는 1 ~ N)
			int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 100,000
			
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				drr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int X = Integer.parseInt(st.nextToken()); // 건물 X, Y의 번호
				int Y = Integer.parseInt(st.nextToken());
				
				adjList[Y].add(X);
			}
			
			int W = Integer.parseInt(in.readLine()); // 건물 W의 번호
			/* -------------- 입력 처리 종료 -------------- */
			
			/* -------------- 위상 정렬 구현 -------------- */
			
//			tempVisited = new boolean[N+1];
			
			rank = new int[N+1];
			Arrays.fill(visited, false);
			sb.append(dfs(W, drr, adjList, visited)).append("\n");
		} // end of for testCase
		
		System.out.print(sb.toString());
	} // end of main
	
	/**	사이클 처리용 방문 배열, 임시적으로 방문한 노드를 저장한다. */
//	static boolean[] tempVisited;
	private static int[] rank;
	
	public static int dfs(int v, int[] drr, ArrayList<Integer>[] adjList, boolean[] visited) {
		
		visited[v] = true;
		
//		tempVisited[v] = true;
		
		int d = drr[v];
		
		int time = 0;
		for (int v2: adjList[v]) {
			
//			if (tempVisited[v2]) {
//				temp = 1000000000;
//				break;
//			}
			
			if (!visited[v2])
				time = Math.max(time, dfs(v2, drr, adjList, visited));
			else
				time = Math.max(time, rank[v2]);
		}
		
//		visited[v] = false;
		
		rank[v] = d + time;
		return rank[v];
	} // end of dfs
	
} // end of class
