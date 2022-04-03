import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_17135_캐슬디펜스_골드4_이진오_84ms
 * 
 *  - 아이디어
 *    : M개의 자리 중 3개의 자리를 뽑는다 => 조합
 *    : 뽑은 자리에 대해서, 
 *      1) 새로운 map을 생성한다. 
 *      2) 새로운 map에 대해서 게임을 진행한다. 
 *      3) 궁수의 위치를 1씩 위로 올리며 N번의 턴을 진행한다. 
 * 
 */ 
public class Main_백준_17135_캐슬디펜스_골드4_이진오_84ms {
	static int N;
	static int M;
	static int D;
	static int result;
	static char[][] map;
	static char[][] temp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		temp = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(2*j);
			}
		}
		
		result = 0;
		int[] archors = new int[3];
		combination(0, 0, archors);
		
		System.out.println(result);
	} // end of main
	
	public static void combination(int start, int cnt, int[] archors) {
		// base case
		if (cnt == 3) {
			play(archors);
			return;
		}
		// recursion
		for (int i = start; i < M; i++) {
			archors[cnt] = i;
			combination(i+1, cnt+1, archors);
		}
	} // end of combination

	public static void play(int[] archors) {
		int res = 0;
		
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, M);
		}
		int[][] coords = new int[3][];
		// r: archor의 행 위치
		for (int r = N-1; r >= 0; r--) {
			for (int c = 0; c < 3; c++) {
				coords[c] = traverse(r, archors[c]);
			}
			for (int i = 0; i < coords.length; i++) {
				int tr = coords[i][0];
				int tc = coords[i][1];
				if (tr < 0 || tc < 0) continue;
				if (temp[tr][tc] == '1') {
					temp[tr][tc] = '0';
					res++;
				}
			}
		}
		
		
		if (res > result) result = res;
	} // end of play

	public static int[] traverse(int r, int c) {
out:	for (int dist = 0; dist < D; dist++) {
			int tr = r;
			int tc = c - dist;
			for (int d = 0; d < dist; d++) {
				if (tr>=0 && tc>=0 && temp[tr][tc] == '1') {
					return new int[] {tr, tc};
				}
				tr--;
				tc++;
			}
			for (int d = 0; d <= dist; d++) {
				if (tr>=0 && tc<M && temp[tr][tc] == '1') {
					return new int[] {tr, tc};
				}
				tr++;
				tc++;
			}
		}
		
		return new int[] {-1, -1};
	} // end of traverse
} // end of class
