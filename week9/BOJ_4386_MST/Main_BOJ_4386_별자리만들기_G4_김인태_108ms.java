import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.StringTokenizer;

public class Main_BOJ_4386_별자리만들기 {

	static class Star{
		int idx;
		double x;
		double y;
		public Star(int idx, double x, double y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Star [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static class Edge {
		int from;
		int to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}		
	}
	
//	단위집합 생성
	public static void makeSet() {
		parents = new int[N];
		//	자신의 부모노드를 자신의 값으로 세팅
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
	}
	
	// a의 집합찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if(a==parents[a])	return a;
		return parents[a] = findSet(parents[a]);	//	path compression
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static ArrayList<Edge> edgeList;
	static Star[] stars, nums;
	static int N;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
				
		stars = new Star[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new Star(i, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
//		for(int i=0; i<N; i++) {
//			System.out.println(stars[i]);
//		}
		
		nums = new Star[2];
		edgeList = new ArrayList<Edge>();
		comb(0, 0);
		
		edgeList.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				double w1 = Math.floor(o1.weight * 100) / 100.0;
				double w2 = Math.floor(o2.weight * 100) / 100.0;
				
				if(w1 > w2) return 1;
				else if(w1 == w2) return 0;
				else return -1;
			}
		});
		
//		System.out.println();
//		for (Edge edge : edgeList) {
//			System.out.println(edge);
//		}
		
		makeSet();
		
		double result=0.0;
		int cnt = 0;
		
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++cnt == N-1) break;
			}
		}	//	신장트리 완성
		
		System.out.println(result);
	}

	public static void comb(int start, int cnt) {
		if(cnt==2) {
			edgeList.add(new Edge(nums[0].idx, nums[1].idx, dist(nums[0], nums[1])));
			return;
		}
		for(int i=start; i<N; i++) {
			nums[cnt] = stars[i];
			comb(i+1, cnt+1);
		}
	}
	
	public static double dist(Star a, Star b) {
		return Math.floor(Math.sqrt(Math.pow(a.x-b.x, 2)+Math.pow(a.y-b.y, 2))*100) / 100.0;
	}
}
