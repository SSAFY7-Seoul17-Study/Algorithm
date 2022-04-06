import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_11066_파일합치기_골드3_이진오_832ms
 * 
 *  - 자료구조
 *    : file[] = 파일의 크기
 *    : ram[][] = i ~ j번째 파일까지 램에 올려두기 위한 비용 (ram[i][j] = file[i] + file[i+1] + ... + file[j])
 *  
 *  - 점화식
 *    : f(i, j) = i번째 파일부터 j번째 파일까지 하나의 파일로 합치기 위한 최소 비용
 *      = ram[i][j] + min( f(i, i) + f(i+1, j), f(i, i+1) + f(i+2, j), ..., f(i, j-1) + f(j, j))
 *      
 *  - dp
 *    : 하향식 - 972ms
 *    : 상향식 - 832ms
 *    
 */
public class Main_백준_11066_파일합치기_골드3_이진오_832ms {
	
	private static int[] file;
	private static int[][] dp;
	private static int[][] ram;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(in.readLine());
			file = new int[N];
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < file.length; i++) {
				file[i] = Integer.parseInt(st.nextToken());
			}
			
			ram = new int[N][N];
			dp = new int[N][N];
			
			for (int i = 0, end = ram.length-1; i < end; i++) {
				ram[i][i+1] = file[i] + file[i+1];
				for (int j = i+2; j < ram.length; j++) {
					ram[i][j] = ram[i][j-1] + file[j];
				}
			}
			
			// dp 상향식
			for (int di = 1; di < dp.length; di++) {
				for (int i = 0, end = dp.length-di; i < end; i++) {
					int min = dp[i][i] + dp[i+1][i+di];
					for (int j = 1; j < di; j++) {
						int temp = dp[i][i+j] + dp[i+j+1][i+di];
						if (temp < min)
							min = temp;
					}
					dp[i][i+di] = ram[i][i+di] + min;
				}
			}
			
			sb.append(dp[0][N-1]).append("\n");
//			sb.append(dfs(0, N-1)).append("\n");
		} // end of for testCase
		
		System.out.print(sb);
		
	} // end of main
	
	/**
	 * dp 하향식
	 */
	public static int dfs(int i, int j) {
		// base case - 파일이 1개일 때
		if (i == j) {
			return 0;
		}
		if (dp[i][j] > 0)
			return dp[i][j];
		
		// 파일 i ~ j를 저장하기 위한 최소 cost
		int cost = ram[i][j];
		
		// 추가 비용 계산
		int addi = dfs(i, i) + dfs(i+1, j);
		for (int k = i+1; k < j; k++) {
			int temp = dfs(i, k) + dfs(k+1, j);
			if (temp < addi)
				addi = temp;
		}
		
		dp[i][j] = cost + addi;
		return dp[i][j];
	} // end of dfs
	
} // end of class
