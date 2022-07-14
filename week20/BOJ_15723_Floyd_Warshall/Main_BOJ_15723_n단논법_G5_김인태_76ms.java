import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 플로이드 와샬로 해결
 * 모든 경로에 대한 최단거리를 구함.
 * 자기 자신에 대한 경로(0)와 경로가 없는 경우(INF) F 
 * 그 외 값은 모두 경로가 존재하는 경우이므로 T 
 * @author kit938639
 *
 */

public class Main_BOJ_15723_n단논법_G5 {

	private static int N;
	private static final int INF = 987654321;
	private static int[][] map, query;
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		map = new int[26][26];
		for(int i=0; i<26; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int to = st.nextToken().charAt(0) - 'a';
			
			map[from][to] = 1;
		}
		
		M = Integer.parseInt(br.readLine());
		
		query = new int[M][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int to = st.nextToken().charAt(0) - 'a';
			
			query[i][0] = from;
			query[i][1] = to;
		}
		
		
		for(int k=0; k<26; k++) {
			for(int i=0; i<26; i++) {
				if(k==i) continue;
				for(int j=0; j<26; j++) {
					if(i==j) continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int from = query[i][0];
			int to = query[i][1];
			if(map[from][to] !=0 && map[from][to] != INF) sb.append("T").append("\n");
			else sb.append("F").append("\n");
		}
		
		System.out.println(sb);
	}

}
