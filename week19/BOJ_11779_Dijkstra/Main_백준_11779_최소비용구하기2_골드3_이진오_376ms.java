import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_백준_11779_최소비용구하기2_골드3_376ms
 * 
 *  0) 문제
 *    : 그래프가 주어졌을 때, 
 *      => 정점 A에서 정점 B까지의 최단 경로, 그리고 그 경로를 추적한다.  
 *  
 *  1) 자료 구조
 *    : int[] dist; dist[i] = 정점 A에서 정점 i까지의 최단 경로
 *    : int[] p; p[v] = 정점 v까지의 최단 경로에 대하여, v에 도달하기 직전에 있던 정점
 * 
 */
public class Main_백준_11779_최소비용구하기2_골드3_이진오_376ms {
	
	static class Node implements Comparable<Node> {
		int v;
		int d;
		
		public Node(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}
	
	static ArrayList<Node>[] adjList;
	
	static int[] dist;
	static int[] p;
	
	static int A;
	static int B;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		dijkstra();
		print();
	}
	
	@SuppressWarnings("unchecked")
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		p = new int[N+1];
		
		adjList = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Node(to, d));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
	}
	
	public static void dijkstra() {
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
		
		dist[A] = 0;
		pQueue.offer(new Node(A, 0));
		
		while (!pQueue.isEmpty()) {
			Node node = pQueue.poll();
			
			if (node.v == B)
				return;
			
			for (Node adj : adjList[node.v]) {
				int d = node.d + adj.d;
				if (d < dist[adj.v]) {
					dist[adj.v]= d;
					p[adj.v] = node.v; 
					pQueue.offer(new Node(adj.v, d));
				}
			}
		}
	}
	
	public static void print() {
		int d = dist[B];
		
		int node = B;
		int cnt = 1;
		
		ArrayList<Integer> pList = new ArrayList<>();
		pList.add(node);
		
		while (node != A) {
			node = p[node];
			pList.add(node);
			cnt++;
		}
		
		Collections.reverse(pList);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(d).append("\n");
		sb.append(cnt).append("\n");
		
		for(int n : pList)
			sb.append(n).append(" ");
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		
		System.out.print(sb.toString());
	}
}
