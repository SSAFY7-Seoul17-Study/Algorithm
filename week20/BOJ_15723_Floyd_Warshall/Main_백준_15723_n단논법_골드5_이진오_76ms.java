import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main_백준_15723_n단논법_골드5_이진오_76ms
 * 
 *  - 플로이드 와샬 알고리즘
 *    : a에서 갈 수 있는 모든 정점을 탐색
 *    : 꼭 최단 경로가 아니어도 가능
 * 
 */
public class Main_백준_15723_n단논법_골드5_이진오_76ms {
	
	static boolean dist[][] = new boolean[26][26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		// n개의 
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			int from = line.charAt(0) - 'a';
			int to = line.charAt(5) - 'a';
			
			dist[from][to] = true;
		}
		
		// 기저 조건
		for (int i = 0; i < 26; i++)
			dist[i][i] = true;
		
		// 플로이드-와샬
		for (int k = 0; k < 26; k++)
			for (int i = 0; i < 26; i++)
				for (int j = 0; j < 26; j++)
					if (dist[i][k] && dist[k][j])
						dist[i][j] = true;
		
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			String line = br.readLine();
			int from = line.charAt(0) - 'a';
			int to = line.charAt(5) - 'a';
			
			sb.append(dist[from][to] ? "T" : "F").append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
}
