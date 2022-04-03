import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1890_점프_실2_추준성_80ms {

	private static int N;
	/*
	 * 0. 규칙 : 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 이동 (한 번 점프할 때 방향 변경 불가) - 반복적인 규칙 => dp 활용
	 * 1. 반드시 오른쪽이나 아래쪽으로만 이동
	 * 2. 0을 만나면 끝 (가장 오른쪽 아래 칸)
	 * 3. 경로의 개수는?
	 * 
	 * 경로의 개수 <= 2^63-1 : long 타입 변수 필요 
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] dp = new long[N][N]; // 정의 : (r, c)까지 도달하는 총 경로의 개수
		dp[0][0] = 1; // 시작점(초기값)은 1
		
		// 시작점을 기준으로 하여 각 좌표까지 도달하는 총 경로의 개수를 각 좌표에 해당하는 dp 테이블에 담음 
		// "하나의 동적인 점"이 아니라 "모든 각 정점"의 관점에서 해석
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int next = board[r][c];
				int nc = c+next;
				int nr = r+next;
				
				if(next == 0) continue; // board[r][c] 값이 0이면 이동하지 않으므로 continue
				
				if(nr < N) dp[nr][c] += dp[r][c];
				if(nc < N) dp[r][nc] += dp[r][c];
			}
		}
		
		System.out.println(dp[N-1][N-1]);
		
	} // end of main
} // end of class
