package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1717_집합의표현_G4_김현교_376ms {
	
	static int N;
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		makeSet();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
			case "0":
				union(Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
				break;
			default:
				if (findSet(Integer.parseInt(st.nextToken()))
						!= findSet(Integer.parseInt(st.nextToken())))
					sb.append("NO\n");
				else
					sb.append("YES\n");
				break;
			}
		}
		System.out.print(sb.toString());
	}//end main
	
	static void makeSet() {
		p = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
	}
	
	static int findSet(int n) {
		if (n == p[n])
			return n;
		return p[n] = findSet(p[n]);
	}
	
	static void union(int a, int b) {
		int ar = findSet(a);
		int br = findSet(b);
		p[br] = ar;
	}
}//end class
