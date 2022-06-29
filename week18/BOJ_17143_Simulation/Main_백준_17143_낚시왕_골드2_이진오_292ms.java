import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_17143_낚시왕_골드2_이진오_292ms
 * 
 *  - 시뮬레이션
 *    1) 상어가 r만큼 이동하며, R 혹은 1에 도달한 경우 방향이 바뀔 때
 *      : 2R-2의 주기마다 반복한다. 
 *      
 *        r| 1, ..., R, R+1, ..., 2R-2
 *           1, ..., R, R-1, ..., 2
 *      
 *      * 음수일 때 주의
 * 
 */
public class Main_백준_17143_낚시왕_골드2_이진오_292ms {
	
	static class State {
		
		int r, c;
		int s, d, z; // speed, dir, size
		
		public State(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
		public void next() {
			// 좌표 이동
			this.r += s * dr[d];
			this.c += s * dc[d];
			
			if (dr[d] != 0) {
				if ((r-1) / (R-1) % 2 == 0) {
					if (r <= 0)
						d = rev[d];
					r = 1 + Math.abs((r-1) % (R-1));
				}
				else {
					if (r > 0)
						d = rev[d];
					r = R - Math.abs((r-1) % (R-1));
				}
			}
			
			if (dc[d] != 0) {
				if ((c-1) / (C-1) % 2 == 0) {
					if (c <= 0)
						d = rev[d];
					c = 1 + Math.abs((c-1) % (C-1));
				}
				else {
					if (c > 0)
						d = rev[d];
					c = C - Math.abs((c-1) % (C-1));
				}
			}
		}
	}

	private static int R;
	private static int C;
	private static int[] dr = {0,-1, 1, 0, 0}; // 위 아래 오른쪽 왼쪽
	private static int[] dc = {0, 0, 0, 1,-1};
	private static int[] rev = {0, 2, 1, 4, 3}; // 반대 방향
	
	private static int kg;
	
	private static State[][] map;
	private static State[][] temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new State[R+1][C+1];
		temp = new State[R+1][C+1];
		
		int M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = new State(r, c, s, d, z);
		}
		
		for (int c = 1; c <= C; c++) {
			catchShark(c);
			move();
		}
		
		System.out.println(kg);
	}
	
	public static void catchShark(int c) {
		for (int r = 1; r <= R; r++) {
			State state = map[r][c];
			if (state != null) {
				map[r][c] = null;
				kg += state.z;
				return;
			}
		}
	}
	
	public static void move() {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				State state = map[r][c];
				
				if (state == null)
					continue;
				
				state.next();
				
				State next = temp[state.r][state.c];
				
				if (next == null || next.z < state.z) {
					temp[state.r][state.c] = state;
				}
				else {
					state = null;
				}
			}
		}
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				map[r][c] = null;
				if (temp[r][c] != null) {
					map[r][c] = temp[r][c];
					temp[r][c] = null;
				}
			}
		}
	}
}
