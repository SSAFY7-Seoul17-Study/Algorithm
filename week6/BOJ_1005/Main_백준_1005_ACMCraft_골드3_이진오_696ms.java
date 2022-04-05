import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Main_백준_1005_ACMCraft_골드3_이진오_696ms
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
public class Main_백준_1005_ACMCraft_골드3_이진오_696ms {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 0; testCase < T; testCase++) {
			/* -------------- 입력 처리 시작 -------------- */
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken()); // 2 <= N <= 1,000 (건물 번호는 1 ~ N)
			int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 100,000
			
			st = new StringTokenizer(in.readLine(), " ");
			int[] drr = new int[N+1]; // 각 건물 당 건설에 걸리는 시간
			for (int i = 1; i < drr.length; i++) {
				drr[i] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				adjList.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int X = Integer.parseInt(st.nextToken()); // 건물 X, Y의 번호
				int Y = Integer.parseInt(st.nextToken());
				
				adjList.get(Y).add(X);
			}
			
			int W = Integer.parseInt(in.readLine()); // 건물 W의 번호
			/* -------------- 입력 처리 종료 -------------- */
			
			/* -------------- 위상 정렬 구현 -------------- */
			
			sb.append(dfs(W, drr, adjList, new int[N+1], new boolean[N+1])).append("\n");
		} // end of for testCase
		
		System.out.print(sb.toString());
	} // end of main
	
	public static int dfs(int v, int[] drr, ArrayList<ArrayList<Integer>> adjList, int[] dp, boolean[] visited) {
		visited[v] = true;
		
		int d = drr[v];
		
		int temp = 0;
		for (int v2: adjList.get(v)) {
			if (!visited[v2])
				temp = Math.max(temp, dfs(v2, drr, adjList, dp, visited));
			else
				temp = Math.max(temp, dp[v2]);
		}
		
		dp[v] = d + temp;
		return dp[v];
	} // end of dfs
	
} // end of class
