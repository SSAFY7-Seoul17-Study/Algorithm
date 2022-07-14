import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_10159_저울_G3 {

	private static final int INF = 987654321;
	private static int N;
	private static int[][] map;
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				map[from][to] = 1;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k==i) continue;
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				if(map[i][j] == INF && map[j][i] != INF) map[i][j] = map[j][i];
				else if(map[j][i] == INF && map[i][j] != INF) map[j][i] = map[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			int cnt = 0;
			for(int j=1; j<=N; j++) {
				if(i != j && map[i][j] == INF) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
		
	}

}
