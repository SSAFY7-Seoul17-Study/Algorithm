package 백준.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_16398_행성연결_G4_김현교_744ms {

	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int cur = 0;
		long sum = 0;
		
		boolean[] visited = new boolean[N];
		visited[cur] = true;
		
		int cnt = 0;
		while (true) {
			for (int to = 0; to < N; to++) {
				if (adj[cur][to] > 0 && !visited[to])
					pq.add(new Edge(to, adj[cur][to]));
			}
			
			while (!pq.isEmpty()) {
				Edge next = pq.poll();
				
				if (visited[next.getTo()]) continue;
				
				visited[next.getTo()] = true;
				cur = next.getTo();
				sum += next.getWeight();
				cnt++;
				break;
			}
			
			if (cnt == N - 1) break;
		}
		
		System.out.println(sum);
	} //end main
	
	static class Edge implements Comparable<Edge> {
		private int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return getWeight() - o.getWeight();
		}

		public int getTo() {
			return to;
		}

		public int getWeight() {
			return weight;
		}
	}
}//end class
