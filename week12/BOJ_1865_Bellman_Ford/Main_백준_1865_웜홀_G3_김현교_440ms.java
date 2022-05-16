package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 벨만포드
 * 
 * 음의 사이클이 있는 경우를 판별하는 문제
 * 
 */

public class Main_백준_1865_웜홀_G3_김현교_440ms {
	
	static List<List<Node>> road;
	static int N, M, W;
	static final int MAX = 5000000;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			road = new ArrayList<>(N + 1);
			for (int i = 0; i <= N; i++) {
				road.add(new ArrayList<Node>());
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				road.get(s).add(new Node(e, t));
				road.get(e).add(new Node(s, t));
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = -1 * Integer.parseInt(st.nextToken());
				
				road.get(s).add(new Node(e, t));
			}
			
			sb.append(bellmanford() ? "YES\n" : "NO\n");
		}
		System.out.print(sb.toString());
	}//end main
	
	static boolean bellmanford() {
		int[] dist = setDistArray();
		
		for (int i = 0; i < N - 1; i++) {
			findShortestDist(dist);
		}
		
		for (int cur = 1; cur <= N; cur++) {
			for (Node next : road.get(cur)) {
				if (dist[next.no] > dist[cur] + next.dist)
					return true;
			}
		}
		
		return false;
	}

	private static void findShortestDist(int[] dist) {
		for (int cur = 1; cur <= N; cur++) {
			for (Node next : road.get(cur)) {
				if (dist[next.no] > dist[cur] + next.dist)
					dist[next.no] = dist[cur] + next.dist;
			}
		}
	}

	private static int[] setDistArray() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, MAX);
		dist[1] = 0;
		return dist;
	}

	static class Node {
		int no, dist;

		public Node(int no, int dist) {
			this.no = no;
			this.dist = dist;
		}
		
	}
}//end class
