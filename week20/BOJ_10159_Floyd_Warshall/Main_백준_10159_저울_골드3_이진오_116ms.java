import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_10159_저울_골드3_이진오_116ms
 * 
 *  - 플로이드 와샬
 *    : DP의 개념을 이용해, 그래프의 모든 정점 사이의 최단 경로를 구한다. 
 *    : if (dist[i][j] > dist[i][k] + dist[k][j])
 *        then dist[i][j] = dist[i][k] + dist[k][j];
 *    
 *      => 그래프 내에 음의 사이클이 존재하지 않아야 한다.
 *      => k를 거쳐 가는 경로가 더 짧을 경우, (i, j) 사이의 최단 경로를 수정한다. 
 *   
 *    Q. 언제 쓰는가?
 *      : 그래프에 음의 사이클이 존재하지 않는 경우 (다익스트라보다 조건이 더 유하다. )
 *      : 그래프의 전체 노드에 대하여, 최단 경로의 정보를 필요로 하는 경우
 *        => 현재 문제에서는, 경로에 대한 정보만을 필요로 하지만, 최단 경로의 정보에서 이끌어낼 수 있다. 
 * 
 */
public class Main_백준_10159_저울_골드3_이진오_116ms {
	
	static int[][] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		dist = new int[N][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (i != j)
					dist[i][j] = 100;
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			dist[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		
		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (dist[i][j] == 100 && dist[j][i] == 100)
					cnt++;
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
