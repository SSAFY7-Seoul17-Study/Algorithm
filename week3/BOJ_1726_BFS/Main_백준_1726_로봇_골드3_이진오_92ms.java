import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Main_백준_1726_로봇_골드3_이진오_92ms
 * 
 *  - 자료구조
 *    : Coord 클래스 = 좌표 / 방향을 저장하기 위한 자료 구조
 *    : 방문 처리 = visited[r][c][5], visited[r][c][0]에는 좌표 방문 처리를 / visited[r][c][1...4]에는 방향 방문 처리를 
 *    
 *  - bfs 
 *    : (r, c, dir)이 주어졌을 때 다음 번 가능한 좌표: (r+dr, c+dr, dir) / (r, c, nextDir)
 * 
 */
public class Main_백준_1726_로봇_골드3_이진오_92ms {
	static class Coord {
		int r, c;
		int dir; // 동, 서, 남, 북 = 1, 2, 3, 4;
		public Coord(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Coord [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
	}
	// dr, dc 벡터
	static int[][] drdc = {
			{}, {0, 1}, {0,-1}, {1, 0}, {-1, 0}
	};
	// {동서}, {남북}
	static int[][] ewsn = {{1, 2}, {3, 4}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken()); // 1 <= R, C <= 100
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R+1][C+1];
		boolean[][][] visited = new boolean[R+1][C+1][5];
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= C; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int r_ = Integer.parseInt(st.nextToken());
		int c_ = Integer.parseInt(st.nextToken());
		int dir_ = Integer.parseInt(st.nextToken());
		Coord start = new Coord(r_, c_, dir_);
		
		st = new StringTokenizer(br.readLine(), " ");
		r_ = Integer.parseInt(st.nextToken());
		c_ = Integer.parseInt(st.nextToken());
		dir_ = Integer.parseInt(st.nextToken());
		Coord end = new Coord(r_, c_, dir_);
		
		// bfs
		ArrayDeque<Coord> queue = new ArrayDeque<Coord>();
		
		queue.offer(start);
		visited[start.r][start.c][0] = true;
		visited[start.r][start.c][start.dir] = true;
		
		int inst = 0;
		boolean flag = false;
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			while (size-- > 0) {
				Coord temp = queue.poll();
				r_ = temp.r;
				c_ = temp.c;
				dir_ = temp.dir;
				int dr_ = drdc[dir_][0];
				int dc_ = drdc[dir_][1];				
				
				// 원하는 좌표 탐색 완료
				if (r_ == end.r && c_ == end.c && dir_ == end.dir) {
					flag = true;
					break;
				}
				
				// dr, dc로 이동
				for (int k = 1; k <= 3; k++) {
					int tr_ = r_ + k*dr_;
					int tc_ = c_ + k*dc_;
					if (tr_> 0 && tc_> 0 && tr_<= R && tc_ <= C &&
							map[tr_][tc_] == '0') {
						if (!visited[tr_][tc_][0]) {
							queue.offer(new Coord(tr_, tc_, dir_));
							visited[tr_][tc_][0] = true;
							visited[tr_][tc_][dir_] = true;							
						}
					}
					// 1만큼 이동이 불가능하다면, 2 혹은 3만큼 이동도 불가능하다. 
					else {
						break;
					}
				}
				
				// 방향 전환 - 방향 전환은 방문 처리의 영향을 받지 않는다. 
				int[] ewsn_ = dir_ <= 2 ? ewsn[1]: ewsn[0];
				for (int tdir_ : ewsn_) {
					if (!visited[r_][c_][tdir_]) {
						queue.offer(new Coord(r_, c_, tdir_));
						visited[r_][c_][tdir_] = true;
					}
				}
			} // end of while size
			
			if (flag) break;
			
			inst++;
		} // end of while queue
		
		System.out.println(inst);
	} // end of main
} // end of class
