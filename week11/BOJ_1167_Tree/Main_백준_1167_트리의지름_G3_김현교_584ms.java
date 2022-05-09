package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @See https://moonsbeen.tistory.com/101
 * 1. 트리의 정의 : 사이클이 없이 모든 정점이 연결되어있는 그래프이다. 사이클이 없는 그래프이므로 정점의 개수가 V개이면 간선의 개수는 V-1개이다.
 *	=> 사이클은 고려하지 않는다.
 * 
 * 2. 각 정점에서의 최대 거리에 있는 정점은 항상 최대 거리를 가지는 정점 2개 중 1개를 포함한다.
 *  => 임의의 점에서 최대 거리에 있는 정점을 구하고, 그 정점에서 최대거리에 있는 정점을 구하면 그 거리가 최대거리가 된다.
 *  
 * 3. 방문체크를 굳이 boolean배열로 하지 않고, 이전에 방문했던 노드 번호만 체크하면 된다.
 */

public class Main_백준_1167_트리의지름_G3_김현교_584ms {

	static int V, max, maxNode;
	static List<Node>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		list = new List[V + 1];
		for (int i = 1; i <= V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			list[n] = new ArrayList<Node>();
			while (true) {
				int next = Integer.parseInt(st.nextToken());
				if (next == -1) break;
				int dist = Integer.parseInt(st.nextToken());
				list[n].add(new Node(next, dist));
			}
		}
		
		dfs(1, 0, 0);
		dfs(maxNode, 0, 0);
		
		System.out.println(max);
	}//end main
	
	static void dfs(int cur, int pre, int dist) {
		
		if (max < dist) {
			maxNode = cur;
			max = dist;
		}
		
		for (Node next : list[cur]) {
			if (next.no != pre)
				dfs(next.no, cur, dist + next.dist);
		}
	}
	
	static class Node {
		int no, dist;

		public Node(int no, int dist) {
			this.no = no;
			this.dist = dist;
		}
		
	}
}//end class
