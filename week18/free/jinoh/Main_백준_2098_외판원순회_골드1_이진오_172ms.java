import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_2098_외판원순회_골드1_이진오_172ms
 * 
 *  - 너무 어렵...
 *  
 *  - dp 테이블
 *    : 0으로 초기화, 순회가 불가능한 상태의 경우 -1
 *    
 *  - dfs(city, visited)
 *    = min(dfs(next, visited | 1<<next) + w[city][next])
 *      where w[city][next] > 0 && (visited & 1<<next) == 0
 *  
 *  - base case
 *    1) if dp[city][visited] != 0 then return dp[city][visited]
 *    2) if visited == (1<<N) - 1 then return -1 if w[city][0] == 0 else w[city][0]
 *    
 *  - debug note
 *    1) dp[city][visited] != 0 에서 아무것도 검출하지 못한 코드
 *       => city에 대한 방문 처리 이후에 dp[city][visited] != 0을 처리해주어야 한다.
 * 
 */
public class Main_백준_2098_외판원순회_골드1_이진오_172ms {
	
	private final static int MAX = 16000001;
	
	private static int N;
	private static int w[][];
	private static int dp[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		w = new int[N][N];
		dp = new int[N][1<<N];
		
		for (int i = 0; i < w.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < w[i].length; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 1<<0));
	}
	
	
	public static int dfs(int city, int visited) {
		// 기저 조건
		if (dp[city][visited] != 0) {
			return dp[city][visited];
		}
		
		if (visited == (1<<N) - 1) {
			return dp[city][visited] = (w[city][0] == 0 ? -1 : w[city][0]);
		}
		
		int min = MAX;
		for (int d = 0; d < N; d++) {
			if ((visited & 1<<d) != 0 || w[city][d] == 0)
				continue;
			int temp = dfs(d, visited | 1<<d);
			if (temp > 0 && temp + w[city][d] < min) {
				min = temp + w[city][d];
			}
		}
		
		return dp[city][visited] = min == MAX ? -1 : min;
	}
	
}
