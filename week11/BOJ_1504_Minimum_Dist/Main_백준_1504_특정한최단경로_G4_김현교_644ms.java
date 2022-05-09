package ShortestPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_1504_특정한최단경로_G4_김현교_644ms {

	private static boolean[] v;
	private static int[] dists;
	static List<List<Node>> list;
	static PriorityQueue<Node> pq;
	
	static final int INF = 200000001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			list.get(n1).add(new Node(n2, dist));
			list.get(n2).add(new Node(n1, dist));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		v = new boolean[N + 1];
		dists = new int[N + 1];
		pq = new PriorityQueue<>();
		
		int res1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		int res2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		int res = Math.min(res1, res2);
		
		System.out.println(res >= INF ? -1 : res);
	}//end main
	
	private static int dijkstra(int start, int end) {
		Arrays.fill(v, false);
		Arrays.fill(dists, INF);
		pq.offer(new Node(start, 0));
		dists[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (v[cur.no]) continue;
			v[cur.no] = true;
			
			for (Node next : list.get(cur.no)) {
				if (!v[next.no] && dists[next.no] > dists[cur.no] + next.dist) {
					dists[next.no] = dists[cur.no] + next.dist;
					pq.offer(new Node(next.no, dists[next.no]));
				}
			}
		}
		
		return dists[end];
	}

	static class Node implements Comparable<Node> {
		int no, dist;

		public Node(int no, int dist) {
			this.no = no;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return dist - o.dist;
		}
	}
	
}//end class
/*
 * 
 * 1 ~ N까지 최단 경로
 * 
 * 다익스트라 원리 -> 시작점에서 갈 수 있는 점 거리 갱신하고 최소거리로 이동 -> 갈 수 있는 거리 갱신하고 방문하지 않은 곳 중 최소 거리로 이동
 * 
 * 쪼개서 생각
 * - 1 -> v1 -> v2 -> N 
 * - 1 -> v2 -> v1 -> N
 * 
 * */

