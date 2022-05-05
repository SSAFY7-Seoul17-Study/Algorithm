import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Main_백준_1167_트리의지름_골드3_이진오_728ms
 * 
 *  - 루트 정점의 지름
 *    1) 리프 정점와의 최대 거리(1)
 *    2) 리프 정점와의 최대 거리(2)
 *      => 둘 다 존재할 경우, max1 + max2
 *      => 하나만 존재할 경우, max1
 *      => 없을 경우 0
 * 
 */
public class Main_백준_1167_트리의지름_골드3_이진오_728ms {
	
	private static int diameter;
	
	private static boolean[] visited;
	private static ArrayList<ArrayList<int[]>> adjList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int V = Integer.parseInt(in.readLine()); // 정점의 개수 V <= 100,000
		
		adjList = new ArrayList<ArrayList<int[]>>(V+1); // 트리를 표현할 인접 행렬
		visited = new boolean[V+1];
		
		// 인접 행렬 초기화
		adjList.add(null);
		for (int i = 1; i <= V; i++) {
			adjList.add(new ArrayList<int[]>());
		}
		
		// 입력 처리
		for (int i = 1; i <= V; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			int v1 = Integer.parseInt(st.nextToken());
			
			ArrayList<int[]> temp = adjList.get(v1);
			while (true) {
				int v2 = Integer.parseInt(st.nextToken());
				if (v2 < 0)
					break;
				
				int e = Integer.parseInt(st.nextToken());
				temp.add(new int[] {v2, e});
			}
		}
		
		int v = 1;
		dfs(v);
		
		System.out.println(diameter);
	}
	
	/**
	 * @param v
	 * @return 정점 v로부터 리프 노드까지의 최대 거리
	 */
	public static int dfs(int v) {
		// 방문 처리
		visited[v] = true;
		
		// 정점 v의 인접 리스트 
		ArrayList<int[]> list = adjList.get(v);
		
		// 최대 거리 1, 2
		int max1 = 0;
		int max2 = 0;
		
		for (int[] edge : list) {
			int to = edge[0];
			int w = edge[1];
			if (!visited[to]) { // 방문하지 않은 정점의 경우, 자식 정점이 된다. 
				int dist = w + dfs(to);
				if (dist > max1) {
					max2 = max1;
					max1 = dist;
				} else if (dist > max2) {
					max2 = dist;
				}
			}
		}
		
		// 트리의 지름
		if (max1 + max2 > diameter)
			diameter = max1 + max2;
		
		// v와 리프 노드와의 최대 거리
		return max1;
	}
}
