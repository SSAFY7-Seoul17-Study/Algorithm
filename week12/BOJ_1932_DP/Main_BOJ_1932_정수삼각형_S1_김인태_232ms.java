import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dp
 * @author kit938639
 *
 */
public class Main_BOJ_1932_정수삼각형_S1 {

	private static int N;
	private static int[][] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		dp = new int[N][N];
//		/ 모양으로 초기화 작업, 누적해서 계속 더해간다.
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int j=0;
			while(st.hasMoreTokens()) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(j==0) {
					if(i==0) {
						dp[0][0] = arr[0][0];
					}else {
						dp[i][j] = dp[i-1][j] + arr[i][j];						
					}
				}
				j++;
			}
		}

//		좌측 빗변(/)부터 우측 빗변(\) 사이의 값은 서로 다른 두개의 출발지와 현재 값을 각각 더한 값 중 최댓값을
//		우측 빗변(\) 값은 계속 누적해 더한다.
		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(i==j) {
					dp[i][j] = dp[i-1][j-1] + arr[i][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j-1]+arr[i][j], dp[i-1][j]+arr[i][j]);
				}
			}
		}

//		제일 밑변인 N-1번째 항들 중 최댓값이 합이 최대가 되는 경로에 있는 수의 합니다.
		int ans=0;
		for(int i=0; i<N; i++) {
			if(ans<dp[N-1][i]) ans = dp[N-1][i];
		}
		System.out.println(ans);
	}

}
