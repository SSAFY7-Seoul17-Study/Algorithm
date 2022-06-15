import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Main_백준_1799_비숍_골드1_이진오_1392ms
 */
public class Main_백준_1799_비숍_골드1_이진오_1392ms {
	
	static int N;
	static int max;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> board;
	
	static int[] dr = {1, 1,-1,-1};
	static int[] dc = {1,-1, 1,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[2*N-1];
		board = new ArrayList<ArrayList<Integer>>();
		
		for (int k = 0; k < 2*N-1; k++)
			board.add(new ArrayList<Integer>());
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < N; j++) {
				if (line.charAt(j<<1) == '1')
					board.get(i+j).add(i-j + N-1);
			}
		}
		
		go(0, 0);
		
		System.out.println(max);
	}
	
	static void go(int rcPlus, int cnt) {
		if (rcPlus == 2*N-1) {
			if (cnt > max)
				max = cnt;
			return;
		}
		
		if (max >= cnt + 2*N-1 - rcPlus)
			return;
		
		for (int rcMinus : board.get(rcPlus)) {
			if (visited[rcMinus])
				continue;
			
			visited[rcMinus] = true;
			go(rcPlus + 1, cnt + 1);
			visited[rcMinus] = false;
		}
		
		go(rcPlus + 1, cnt);
	}
	
	static void print() {
		System.out.println("------------------------------");
		for (ArrayList<Integer> list : board)
			System.out.println(list);
	}
}
