import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_1915_가장큰정사각형_골드4_이진오_240ms
 * 
 *  - dp
 *    : if sq[i][j] == 1
 *          sq[i][j] = Math.min(sq[i+1][j], sq[i][j+1], sq[i+1][j+1]) + sq[i][j]
 * 
 */
public class Main_백준_1915_가장큰정사각형_골드4_이진오_240ms {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] sq = new int[N][M];
		
		for (int i = 0; i < sq.length; i++) {
			String line = in.readLine();
			for (int j = 0; j < sq[i].length; j++) {
				sq[i][j] = line.charAt(j) - '0';
			}
		}
		
		int max = sq[N-1][M-1];
		for (int i = M-2; i >= 0; i--) {
			max = Math.max(max, sq[N-1][i]);
		}
		for (int i = N-2; i >= 0; i--) {
			max = Math.max(max, sq[i][M-1]);
		}
		
		for (int i = N-2; i >= 0; i--) {
			for (int j = M-2; j >= 0; j--) {
				if (sq[i][j] == 1) {
					sq[i][j] = 1 + Math.min(sq[i+1][j], Math.min(sq[i][j+1], sq[i+1][j+1]));
					if (sq[i][j] > max)
						max = sq[i][j];
				}
			}
		}
		
		System.out.println(max*max);
	}
}
