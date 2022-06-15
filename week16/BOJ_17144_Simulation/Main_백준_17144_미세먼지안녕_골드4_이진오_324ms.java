import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_17144_미세먼지안녕_골드4_324ms
 * 
 *  - 시뮬레이션
 *    1) 미세먼지 확산
 *      : 현재 지도의 상태(map) -> 확산된 미세먼지의 상태(temp)
 *      : map += temp
 *      : temp 초기화
 *      
 *    2) 공기청정기 가동
 *      : 2차원 배열의 회전
 * 
 */
public class Main_백준_17144_미세먼지안녕_골드4_이진오_324ms {
	
	static int R;
	static int C;
	static int T;
	
	static int cleanerR1;
	static int cleanerR2;
	
	static int[][] map;
	static int[][] temp;
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	static void spread() {
		// map -> temp
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				if (map[r][c] > 0) {
					int cnt = 0;
					int dust = map[r][c] / 5;
					for (int i = 0; i < dr.length; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if (nr >= 0 && nr < R && nc >= 0 && nc < C
								&& map[nr][nc] >= 0) {
							cnt++;
							temp[nr][nc] += dust;
						}
					}
					map[r][c] -= dust * cnt;
				}
		
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				if (temp[r][c] > 0)
					map[r][c] += temp[r][c];
		
		for (int r = 0; r < R; r++)
			Arrays.fill(temp[r], 0);
	}
	
	static void clean() {
		int r = cleanerR1 + 1;
		int c = 0;
		
		while (r > 0)
			map[r][c] = map[--r][c];
		while (c < C-1)
			map[r][c] = map[r][++c];
		while (r < cleanerR1)
			map[r][c] = map[++r][c];
		while (c > 1)
			map[r][c] = map[r][--c];
		map[r][c--] = 0;
		map[r++][c] = -1;
		
		while (r < R-1)
			map[r][c] = map[++r][c];
		while (c < C-1)
			map[r][c] = map[r][++c];
		while (r > cleanerR2)
			map[r][c] = map[--r][c];
		while (c > 1)
			map[r][c] = map[r][--c];
		map[r][c--] = 0;
		map[r][c] = -1;
		
	}
	
	static void print(String msg) {
		System.out.println("-----" + msg + "-----");
		for (int r = 0; r < R; r++)
			System.out.println(Arrays.toString(map[r]));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		temp = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				int val = Integer.parseInt(st.nextToken());
				map[r][c] = val;
				if (val < 0 && cleanerR1 == 0)
					cleanerR1 = r;
				else if (val < 0)
					cleanerR2 = r;
			}
		}
		
		for (int t = 0; t < T; t++) {
			spread();
			clean();
		}
		
		int dusts = 0;
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				if (map[r][c] > 0)
					dusts += map[r][c];
		
		System.out.println(dusts);
	}
	
}
