import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_1043_거짓말_골드4_이진오_76ms
 * 
 *  - 유니온 파인드(서로소 집합)
 * 
 */
public class Main_백준_1043_거짓말_골드4_이진오_76ms {
	
	static int[] p;
	
	public static int find(int x) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x]);
	}
	
	public static void union(int x, int y) {
		int reprX = find(x);
		int reprY = find(y);
		if (reprX == reprY)
			return;
		
		p[reprY] = reprX;
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		p = new int[N+1];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
		
		// 진실을 아는 사람
		st = new StringTokenizer(in.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람이 존재할 경우
		if (n > 0) {
			// 진실을 아는 사람 중 대표자
			int p = Integer.parseInt(st.nextToken());
			for (int i = 1; i < n; i++) {
				int p0 = Integer.parseInt(st.nextToken());
				union(p, p0);
			}
			
			// 하나의 파티 = 같은 집합에 속해 있음
			int[] parties = new int[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int m = Integer.parseInt(st.nextToken());
				
				int pp = Integer.parseInt(st.nextToken());
				parties[i] = pp;
				for (int j = 1; j < m; j++) {
					int pp0 = Integer.parseInt(st.nextToken());
					union(pp, pp0);
				}
			}
			
			int cnt = 0;
			for (int pp : parties) {
				if (find(p) != find(pp))
					cnt++;
			}
			System.out.println(cnt);
		}
		
		// 진실을 아는 사람이 없을 경우
		else {
			for (int i = 0; i < M; i++) {
				in.readLine();
			}
			System.out.println(M);
		}
	}
	
}
