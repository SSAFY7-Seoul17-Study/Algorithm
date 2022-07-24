import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_16398_행성연결_골드4_이진오_504ms
 * 
 *  - 최소 스패닝 트리(MST)
 *    : PRIM 알고리즘 사용
 *      => 배열을 입력받았기 때문에
 * 
 */
public class Main_백준_16398_행성연결_골드4_이진오_504ms {
	
	private static final int MAX = 987654321;
	
	private static int[][] c;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		c = new int[N][N];
		
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < N; j++)
				c[i][j] = Integer.parseInt(st.nextToken());
			
			dist[i] = MAX;
		}
		
		long total = 0L;
		
		int cnt = N;
		int planet = 0;
		visited[planet] = true;
		
		while (cnt > 1) {
			// 0. dist 배열 업데이트
			int min = MAX;
			int next = planet;
			
			for (int v = 0; v < N; v++) {
				if (v == planet || visited[v])
					continue;
				
				if (c[planet][v] < dist[v])
					dist[v] = c[planet][v];
				
				if (dist[v] < min) {
					min = dist[v];
					next = v;
				}
			}
			
			// 1. 새로운 planet 추가하기
			planet = next;
			visited[planet] = true;
			total += min;
			cnt--;
		}
		
		System.out.println(total);
	}
	
}
