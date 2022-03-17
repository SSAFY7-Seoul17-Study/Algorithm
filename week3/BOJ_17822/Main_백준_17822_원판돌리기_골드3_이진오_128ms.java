import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_17822_원판돌리기_골드3_이진오_128ms
 * 
 *  - 로직 개복잡..
 * 
 */
public class Main_백준_17822_원판돌리기_골드3_이진오_128ms {
	static int M;
	static int N;
	static int[][] negatives;
	static int[] tempNeg;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
		N = Integer.parseInt(st.nextToken()); // 2 <= N, M <= 50
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); // 1 <= T <= 50
		
		boolean[][] visited = new boolean[N+1][M];
		negatives = new int[N+1][M];
		tempNeg = new int[M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				negatives[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = N*M;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dx = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			for (int x = dx; x <= N; x+=dx) {
				rotate(x, d, k);
			}
			for (int r = 1; r <= N; r++) {
				Arrays.fill(visited[r], false);				
			}
			int erased = erase(visited);
			if (erased == 0) {
				regularize(calculateSum(), cnt);
			}
			cnt -= erased;
		}
				
		System.out.println(calculateSum());
		
	} // end of main

	public static void rotate(int n, int d, int k) {
		int[] negative = negatives[n];
		// 시계 방향 회전
		if (d == 0) {
			for (int i = 0; i < M; i++) {
				int temp = i + k;
				if (temp >= M) temp -= M;
				
				tempNeg[temp] = negative[i];
			}
		}
		// 반시계 방향 회전
		else {
			for (int i = 0; i < M; i++) {
				int temp = i - k;
				if (temp < 0) temp += M;
				
				tempNeg[temp] = negative[i];
			}
		}
		// 커밋
		for (int i = 0; i < M; i++) {
			negative[i] = tempNeg[i];
		}
	} // end of rotate
	
	public static int erase(boolean[][] visited) {
		int erased = 0;
		
		for (int r = 1; r <= N; r++) {
			for (int c = 0; c < M; c++) {
				if (negatives[r][c] > 0) {
					int cnt = dfs(r, c, false, visited);
					if (cnt > 1) {
						erased += cnt;
					}
				}
			}
		}
				
		return erased;
	} // end of erase
	
	static int[] dr = {0, 1, 0,-1};
	static int[] dc = {1, 0,-1, 0};
	
	public static int dfs(int r, int c, boolean flag, boolean[][] visited) {
		// 방문 처리
		visited[r][c] = true;
		// 탐색한 좌표의 수
		int cnt = 1;
		
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int tr = r + dr[i];
			int tc = c + dc[i];
			if (tr > 0 && tr <= N) {
				// 원회전
				if (tc < 0) 
					tc += M;
				if (tc >= M) 
					tc -= M;
				// 방문 가능하다면,,
				if (!visited[tr][tc] && negatives[tr][tc] > 0 && 
						negatives[tr][tc] == negatives[r][c]) {
					flag = true;
					cnt += dfs(tr, tc, flag, visited);
				}
			}
		}
		
		// 중복이 존재했었다면
		if (flag)
			negatives[r][c] = 0;
		
		return cnt;
	}
	
	public static int calculateSum() {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				sum += negatives[i][j];
			}
		}
		
		return sum;
	}
	
	public static void regularize(int sum, int cnt) {
		double avg = (double) sum / cnt;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				int num = negatives[i][j];
				if (num != 0) {
					if (num > avg) {
						negatives[i][j] = num-1;
					}
					else if (num < avg) {
						negatives[i][j] = num+1;
					}
				}
			}
		}
	}

} // end of class
