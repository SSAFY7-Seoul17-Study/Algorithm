import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_����_16398_�༺����_���4_������_504ms
 * 
 *  - �ּ� ���д� Ʈ��(MST)
 *    : PRIM �˰��� ���
 *      => �迭�� �Է¹޾ұ� ������
 * 
 */
public class Main_����_16398_�༺����_���4_������_504ms {
	
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
			// 0. dist �迭 ������Ʈ
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
			
			// 1. ���ο� planet �߰��ϱ�
			planet = next;
			visited[planet] = true;
			total += min;
			cnt--;
		}
		
		System.out.println(total);
	}
	
}
