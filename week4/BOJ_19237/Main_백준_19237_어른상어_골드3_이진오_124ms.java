import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_19237_어른상어_골드3_이진오_124ms
 * 
 *  - 자료 구조를 뭘 쓸것이냐
 *    : 지도 - 지도에 존재하는 상어의 번호 / 냄새의 번호 / 냄새의 시간
 *    : 상어 - 좌표 / 현재 방향 정보 / 현재 방향 정보에 따른 다음 방향 정보의 우선 순위
 */
public class Main_백준_19237_어른상어_골드3_이진오_124ms {
	static class Coord {
		int sharkNo;
		int smellNo;
		int t;
		public Coord() {
			this.sharkNo = 0;
			this.smellNo = 0;
			this.t = 0;
		}
	} // end of class Coord
	
	static class Shark {
		int r, c;
		int dir;
		int[][] priorities;
		public Shark() {
			this.r = 0;
			this.c = 0;
			this.dir = 0;
			this.priorities = new int[5][4];
		}		
	} // end of class Shark
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Coord[][] map = new Coord[N][N];
		Shark[] sharks = new Shark[M+1];
		init(map, sharks);
		
		// 지도 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n > 0) {
					map[i][j].sharkNo = n;
					map[i][j].smellNo = n;
					sharks[n].r = i;
					sharks[n].c = j;
				}
			}
		}
		// 상어의 현 방향 정보
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}
		// ...
		for (int x = 1; x <= M; x++) {
			for (int y = 1; y <= 4; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int z = 0; z < 4; z++) {
					int n = Integer.parseInt(st.nextToken());
					sharks[x].priorities[y][z] = n;
				}
			}
		}
		
		System.out.println(play(sharks, map, N, M, k));
		
	} // end of main
	
	// 위, 아래, 왼쪽, 오른쪽
	static int[] dr = {0,-1, 1, 0, 0};
	static int[] dc = {0, 0, 0,-1, 1};
	
	public static int play(Shark[] sharks, Coord[][] map, int N, int M, int k) {
		int time = 0;
		int cnt = M;
		
		while (true) {
			time++;
			// 1. 이동
l1:			for (int i=1; i<= M; i++) {
				Shark shark = sharks[i];
				if (shark == null)
					continue;
				// 현재 상어의 위치
				int r = shark.r;
				int c = shark.c;
				// 현재 방향에 따른, 우선 순위
				int[] priority = shark.priorities[shark.dir];
				// 냄새가 없는 좌표 찾기
				for (int p : priority) { // {1, 3, 2, 4}
					int tr = r + dr[p];
					int tc = c + dc[p];
					if (tr >= 0 && tc >= 0 && tr < N && tc < N && 
							(time - map[tr][tc].t > k || map[tr][tc].t == 0 && map[tr][tc].smellNo == 0)) {
						shark.r = tr;
						shark.c = tc;
						shark.dir = p;
						continue l1;
					}				
				}
				// 냄새가 나랑 같은 좌표 찾기
				for (int p : priority) {
					int tr = r + dr[p];
					int tc = c + dc[p];
					if (tr >= 0 && tc >= 0 && tr < N && tc < N && // 이동 가능하며
							(map[tr][tc].smellNo == i && time - map[tr][tc].t <= k)) { // 나랑 같을 경우
						shark.r = tr;
						shark.c = tc;
						shark.dir = p;
						continue l1;
					}
				}
			}
			// 2. 제거
			Queue<Integer> delQueue = new LinkedList<Integer>();
			
			// 지도 업데이트, 큐에 제거된 상어 enqueue
			// 지도의 상어 정보 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j].sharkNo = 0;
				}
			}
			// 상어 정보를 지도 정보에 넣기
			for (int i = 1; i <= M; i++) {
				Shark shark = sharks[i];
				if (shark == null) continue;
				int r = shark.r;
				int c = shark.c;
				// 번호가 낮은 상어가 자리 선점
				if (map[r][c].sharkNo == 0) {
					map[r][c].sharkNo = i;
				}
				// 선점된 자리라면, 제거
				else {
					delQueue.offer(i);
					cnt--;
				}
			}
			
			// 큐에서 상어 빼면서 상어 배열에서 제거 - null 처리
			for (int i : delQueue) {
				sharks[i] = null;
			}
			
			// 종료 조건 - 1번 상어만 남거나, 시간이 1000초 이상 소요되거나
			if (cnt == 1 || time > 1000)
				break;
			
			// 3. 채취 업데이트
			for (int i = 1; i <= M; i++) {
				Shark shark = sharks[i];
				if (shark == null) continue;
				int r = shark.r;
				int c = shark.c;
				map[r][c].smellNo = i;
				map[r][c].t = time;
			}
		}
		
		if (time > 1000) 
			time = -1;
		
		return time;
	}


	public static void init(Coord[][] map, Shark[] sharks) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = new Coord();
			}
		}
		for (int i = 1; i < sharks.length; i++) {
			sharks[i] = new Shark();
		}
	} // end of init
	
} // end of class
