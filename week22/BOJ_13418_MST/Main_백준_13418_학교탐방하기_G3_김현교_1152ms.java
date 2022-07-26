package 백준.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_13418_학교탐방하기_G3_김현교_1152ms {

	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		List<Edge> maxList = new ArrayList<>(M + 1);
		List<Edge> minList = new ArrayList<>(M + 1);
		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int fatigue = Integer.parseInt(st.nextToken());
			
			maxList.add(new Edge(from, to, fatigue));
			minList.add(new Edge(from, to, fatigue));
		}
		
		Collections.sort(maxList, (o1, o2) -> {
			return o1.getFatigue() - o2.getFatigue();
		});
		Collections.sort(minList, (o1, o2) -> {
			return o2.getFatigue() - o1.getFatigue();
		});
		
		long maxFatigue = kruskal(maxList);
		long minFatigue = kruskal(minList);
		
		System.out.println(maxFatigue - minFatigue);
	} //end main
	
	static long kruskal(List<Edge> edgeList) {
		makeSet();
		
		long res = 0;
		int cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				res += edge.fatigue == 0 ? 1 : 0;
				if (++cnt == N)
					break;
			}
		}
		return res * res;
	}

	static void makeSet() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int n) {
		if (parents[n] == n) 
			return n;
		return parents[n] = findSet(parents[n]);
	}
	
	static boolean union(int a, int b) {
		int aroot = findSet(a);
		int broot = findSet(b);
		if (aroot == broot)
			return false;
		parents[broot] = aroot;
		return true;
	}
	
	static class Edge {
		private int from, to, fatigue;

		public Edge(int from, int to, int fatigue) {
			this.from = from;
			this.to = to;
			this.fatigue = fatigue;
		}

		public int getFrom() {
			return from;
		}

		public int getTo() {
			return to;
		}

		public int getFatigue() {
			return fatigue;
		}
	}
}//end class
/*
 * 최악 -> 가중치 큰 순으로 경로 선정
 * 최선 -> 가중치 작은 순으로 경로 선정
 * 크루스칼 2번 수행
 * */
