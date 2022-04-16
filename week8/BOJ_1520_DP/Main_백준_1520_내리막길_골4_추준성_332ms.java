import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1520_내리막길_골4_추준성_332ms {
	/*
	 * <설계>
	 * 1. 구하고자 하는 것 : 항상 내리막길로만 이동하는 경로의 개수
	 * 2. dfs 4방탐색 (단, 현재 값보다 낮은 값으로만 - 방문 체크 대신의 역할(루프 방지 역할) - 더이상 4방에 나보다 더 작은 값이 없으면 끝남)
	 * 3. 오른쪽 아래까지 도달했을 때 cnt++
	 * 4. 단순 dfs로는 메모리 초과 => 중복된 경로 연산 제거 => 성공한 경로를 기억해야함 => dp(메모이제이션)
	 * 5. 저장된 성공 경로 일부를 만나면 바로 cnt++하고 리턴하도록 함 => 20%에서 시간초과 => 연산 줄이기
	 *
	 * dp[i][j] : (i,j)에서 도착지점까지 도달할 수 있는 경로의 개수
	 */
	
	private static int[] dr = {0, 0, 1,-1};
	private static int[] dc = {1,-1, 0, 0};
	private static int[][] map;
	private static int m;
	private static int n;
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 1 <= m <= 500
		n = Integer.parseInt(st.nextToken()); // 1 <= n <= 500
		
		map = new int[m][n];
		dp = new int[m][n];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1); // dp 배열 값 -1로 초기화
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 1 <= map[i][j] <= 10000
			}
		}
		
		System.out.print(dfs(0,0)); // 재귀로 전달된 값 출력
		
		
	} // end of main
	
	private static int dfs(int r, int c) {
		// 0. 기저 조건 (목적지 도달하면 1 리턴)
		if(r == m-1 && c == n-1) return 1;
		
		// 1. 이동한 위치가 -1이 아니면 dp[r][c] 값을 리턴
		if(dp[r][c] != -1) return dp[r][c]; 
			
		/*
		 * 아래, 이동한 위치가 -1이면 0으로 값을 바꿈 (굳이 앞서 dp 값을 -1로 초기화한 뒤 방문 시 0으로 바꿔주는 이유는, 더 이상 못 감에도 재귀를 타는 걸 방지하기 위함.)
		 * 즉, dp 값이 0(원래 dp 배열 생성시 초기값)일 때 재귀를 다시 도는 현상(==시간초과)을 방지하기 위함
		 */
		dp[r][c] = 0; 
		
		// 2. 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= m || nc < 0 || nc >= n || map[r][c] <= map[nr][nc]) continue;
			
			dp[r][c] += dfs(nr,nc); // dp값 누적해서 시작지점까지 전달 (분기점에서 값이 합쳐짐, 최종적으로 시작지점에 가능한 총 경로의 개수가 반환됨)
		}
		
		// 3. 지속적으로 누적된 dp[r][c] 리턴
		return dp[r][c]; 
	}

} // end of class
