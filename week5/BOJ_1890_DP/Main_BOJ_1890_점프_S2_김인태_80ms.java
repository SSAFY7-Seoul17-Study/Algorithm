import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 경로의 개수는 2^63-1보다 작거나 같다. => 정답 자료형 : long
 * @author kit938639
 *
 */


public class Main_BOJ_1890_점프_S2_김인태_80ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		long[][] dp = new long[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		시간초
//		Queue<int[]> q = new LinkedList<int[]>();
//		q.offer(new int[] {0,0});
//		
//		while(!q.isEmpty()) {
//			int y = q.peek()[0];
//			int x = q.peek()[1];
//			q.poll();
//			
//			if(y==n-1 && x==n-1) continue;
//			int jump = map[y][x];
//			
//			// 오른쪽 점프가 범위 내라면
//			if(x+jump < n) {
//				dp[y][x+jump]++;
//				q.offer(new int[] {y, x+jump});
//			}
//			// 아래쪽 점프가 범위 내라면
//			if(y+jump < n) {
//				dp[y+jump][x]++;
//				q.offer(new int[] {y+jump, x});
//			}
//		}
		
		dp[0][0]=1;
		for(int y=0; y<n; y++) {
			for(int x=0; x<n; x++) {
				if(dp[y][x]==0) continue;
				if(y==n-1 && x==n-1) continue;
				int jump = map[y][x];
				// 오른쪽 점프가 범위 내라면
				if(x+jump < n) {
					dp[y][x+jump]+=dp[y][x];
				}
				// 아래쪽 점프가 범위 내라면
				if(y+jump < n) {
					dp[y+jump][x]+=dp[y][x];
				}
			}
		}
		
//		for(int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(dp[i]));			
//		}
		
		System.out.println(dp[n-1][n-1]);
	}

}
