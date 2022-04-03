import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1717_집합의표현_골4_추준성 {
	/*
	 * 0 - union()
	 * 1 - findSet()
	 */
	public static void makeSet() {
		parents = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
	}

	public static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return;

		parents[bRoot] = aRoot;
	}
	
	public static boolean isSameRoot(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return true;
		else return false;
	}
	
	public static int[] parents;
	public static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		makeSet();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(op == 1) {
				boolean flag = isSameRoot(a, b);
				System.out.println(flag ? "YES" : "NO");
			} else {
				union(a, b);
			}
		}
		
		
	} // end of main

} // end of class
