import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_백준_13418_학교탐방하기_골드3_이진오_584ms
 * 
 *  1) 최적의 경로
 *    : 모든 내리막길에 대하여, union 연산을 진행한다. 
 *      - 연결되지 않은 집합의 개수를 헤아린다. 
 *      - 해당 집합은, 오르막길로 연결되어야만 하는 집합들이 된다. 
 *        => 이를 통해서, 피로도 k를 계산할 수 있다.  
 *  
 *  2) 최악의 경로
 *    : 모든 오르막길에 대하여, union 연산을 진행한다. 
 *      - 연결되지 않은 집합의 개수를 헤아린다. 
 *      - 해당 집합은, 내리막길로 연결되어야만 하는 집합들이 된다.
 *        => 이를 통해서, 피로도 k를 계산할 수 있다.  
 * 
 */
public class Main_백준_13418_학교탐방하기_골드3_이진오_584ms {
	
	private static int[] k;
	private static int[] p;
	
	private static List<int[]> asc;
	private static List<int[]> desc;
	
	private static int N;
	private static int M;

	public static int find(int x) {
		if (p[x] == x)
			return x;
		return p[x] = find(p[x]);
	}
	
	public static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX == rootY)
			return false;
		p[rootY] = rootX;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 건물의 수, 1 <= N <= 1,000
		M = Integer.parseInt(st.nextToken()); // 도로의 수, 1 <= M <= N(N-1)/2
		
		k = new int[2];
		p = new int[N+1];
		
		// 입구와 1번 건물과의 연결 관계, offset
		String line = br.readLine();
		int offset = line.charAt(line.length() - 1) - '0';
		
		Arrays.fill(k, offset == 0 ? 1 : 0);
		
		// 오르막길 및 내리막길에 대한 정보
		asc = new ArrayList<int[]>();
		desc = new ArrayList<int[]>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int v0 = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if (weight == 1)
				desc.add(new int[] {v0, v1});
			else
				asc.add(new int[] {v0, v1});
		}
		
		// p 배열 초기화
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
		// default: 입구와 1번 건물 union
		union(0, 1);
		int cnt = N;
		
		for (int[] edge : desc) {
			if (union(edge[0], edge[1])) {
				cnt--;
			}
		}
		k[0] += (cnt - 1);
		
		// p 배열 초기화
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
		union(0, 1);
		cnt = N;
		
		for (int[] edge : asc) {
			if (union(edge[0], edge[1]))
				cnt--;
		}
		k[1] += (N - cnt);
		
		System.out.println(k[1]*k[1] - k[0]*k[0]);
	}
	
}
