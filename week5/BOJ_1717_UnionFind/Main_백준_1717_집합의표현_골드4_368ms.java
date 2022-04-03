package baekjoon.i;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_1717_집합의표현_골드4_368ms
 * 
 *  - 서로소 집합, 이를 표현하는 방법
 * 
 */
public class Main_백준_1717_집합의표현_골드4_368ms {
	static int[] set;
	
	public static void make(int n) {
		set = new int[n+1];
		for (int i = 1; i < set.length; i++) {
			set[i] = i;
		}
	}
	
	public static int find(int x) {
		if (x == set[x])
			return x;
		return set[x] = find(set[x]);
	}
	
	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX == rootY)
			return;
		set[rootX] = rootY; // x의 대표자의 대표자를, y의 대표자로 치환
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		make(N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			char cmd = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == '0') {
				union(a, b);
			} else {
				sb.append(find(a)==find(b) ? "YES": "NO").append("\n");
			}
		}
		
		System.out.print(sb.toString());
	} // end of main
} // end of class
