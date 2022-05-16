import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_BOJ_2096_내려가기_G4_336ms {

	private static int N;
	private static int[][] arr, maxDp, minDp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		maxDp = new int[N][3];
		minDp = new int[N][3];
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<3; i++) {
			maxDp[0][i] = minDp[0][i] = arr[0][i];
		}
		
		// 최대값 구하기
		for(int i=1; i<N; i++) {
			maxDp[i][0] = Math.max(arr[i][0] + maxDp[i-1][0], arr[i][0] + maxDp[i-1][1]);
			maxDp[i][1] = Math.max(Math.max(arr[i][1] + maxDp[i-1][0], arr[i][1] + maxDp[i-1][1]), arr[i][1] + maxDp[i-1][2]);
			maxDp[i][2] = Math.max(arr[i][2] + maxDp[i-1][1], arr[i][2] + maxDp[i-1][2]);
		}
		// 최솟값 구하기
		for(int i=1; i<N; i++) {
			minDp[i][0] = Math.min(arr[i][0] + minDp[i-1][0], arr[i][0] + minDp[i-1][1]);
			minDp[i][1] = Math.min(Math.min(arr[i][1] + minDp[i-1][0], arr[i][1] + minDp[i-1][1]), arr[i][1] + minDp[i-1][2]);
			minDp[i][2] = Math.min(arr[i][2] + minDp[i-1][1], arr[i][2] + minDp[i-1][2]);
		}
		
		int max = Math.max(Math.max(maxDp[N-1][0], maxDp[N-1][1]), maxDp[N-1][2]);
		int min = Math.min(Math.min(minDp[N-1][0], minDp[N-1][1]), minDp[N-1][2]);
		System.out.println(max+" "+min);
	}

}
