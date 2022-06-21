import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_20055_컨베이어벨트위의로봇_골드5_이진오_200ms
 * 
 *  - 시뮬레이션
 * 
 */
public class Main_백준_20055_컨베이어벨트위의로봇_골드5_이진오_200ms {
	
	/**
	 * 컨베이어 벨트 위 하나의 칸
	 */
	static class Square {
		Conveyor conveyor;
		int durability;
		boolean isLoaded;
		
		public Square(Conveyor conveyor, int durability) {
			this.conveyor = conveyor;
			this.durability = durability;
			this.isLoaded = false;
		}
		
		public boolean isLoadable() {
			return durability > 0 && !isLoaded;
		}
		
		public void load() {
			if (--durability == 0) {
				this.conveyor.cntNotLoadables++;
			}
			isLoaded = true;
		}
		
		public void offLoad() {
			isLoaded = false;
		}
	}
	
	/**
	 * 컨베이어 벨트 객체
	 *   - Square[] belt: 각 칸의 배열
	 *   - N, K: 문제에서 제시된 벨트의 크기, 가능한 내구도
	 *   - cntNotLoadables: 내구도가 바닥난 컨베이어 벨트의 칸
	 *   
	 *   - init(BufferedReader): 벨트 초기화
	 *   - rotate(): 벨트를 한 칸씩 회전
	 *   - moveRobots(): 벨트에 있는 로봇을 한 칸씩 앞으로 이동
	 *   - startUp(): 벨트 가동을 시작
	 */
	static class Conveyor {
		Square[] belt;
		int N, K;
		int cntNotLoadables;
		
		public Conveyor(int N, int K) {
			this.belt = new Square[2*N];
			this.N = N;
			this.K = K;
			this.cntNotLoadables = 0;
		}
		
		public void init(BufferedReader br) throws IOException {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < 2*N; i++)
				belt[i] = new Square(this, Integer.parseInt(st.nextToken()));
		}
		
		public void rotate() {
			Square temp = belt[2*N-1];
			for (int i = 2*N-1; i > 0; i--) {
				belt[i] = belt[i-1];
			}
			belt[0] = temp;
			
			if (belt[N-1].isLoaded)
				belt[N-1].offLoad();
		}
		
		public void moveRobots() {
			for (int i = N-2; i >= 0; i--) {
				if (belt[i].isLoaded && belt[i+1].isLoadable()) {
					belt[i].offLoad();
					belt[i+1].load();
				}
			}
			
			if (belt[N-1].isLoaded)
				belt[N-1].offLoad();
			
			if (belt[0].isLoadable())
				belt[0].load();
		}
		
		public int startUp() {
			int stage = 0;
			
			while (this.cntNotLoadables < K) {
				stage++;
				rotate();
				moveRobots();
			}
			
			return stage;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Conveyor conv = new Conveyor(N, K);
		
		conv.init(br);
		System.out.println(conv.startUp());
	}
	
}
