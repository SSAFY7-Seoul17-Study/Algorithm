import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main_백준_9663_NQueen_골드4_2564ms
 * 
 * 2022-06-09
 * 
 */
public class Main_백준_9663_NQueen_골드4_이진오_2564ms {
	
	static int N;
	static int cnt;
	
	static boolean[] col;
	static boolean[] sum;
	static boolean[] sub;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		col = new boolean[N];
		sum = new boolean[2*N-1];
		sub = new boolean[2*N-1];
		
		dfs(0);
		
		System.out.println(cnt);
	}

	static void dfs(int r) {
		if (r == N) {
			cnt++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			if (check(r, c))
				continue;
			
			update(r, c);
			dfs(r+1);
			update(r, c);
		}
	}
	
	static void update(int r, int c) {
		col[c] = !col[c];
		sum[r+c] = !sum[r+c];
		sub[r-c+N-1] = !sub[r-c+N-1];
	}
	
	static boolean check(int r, int c) {
		return col[c] || sum[r+c] || sub[r-c+N-1];
	}
	
}
