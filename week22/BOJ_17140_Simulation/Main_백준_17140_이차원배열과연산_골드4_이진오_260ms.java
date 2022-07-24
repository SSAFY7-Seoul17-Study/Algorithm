import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_백준_17140_이차원배열과연산_골드4_이진오_260ms
 * 
 *  - 전역 객체
 *    1) int[][] arr: 이차원 배열
 *    2) int rSize: 배열의 행 크기
 *    3) int cSize: 배열의 열 크기
 *    4) int[][] counter: arr에서 나온 값의 개수를 세는 배열
 *    5) PriorityQueue<int[]> pQueue: counter의 값을 기준으로 정렬을 진행
 *  
 *  - 개선 방안?
 *    : 우선 순위 큐를 통해 직접 정렬하기 보다는, 1개인 원소들, 2개인 원소들, ... 등을 저장하여 관리하는 것이 빠를듯?
 */
public class Main_백준_17140_이차원배열과연산_골드4_이진오_260ms {

	static int[][] arr = new int[100][100];
	static int rSize = 3;
	static int cSize = 3;
	
	static int[][] counter = new int[101][2];
	
	static PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>((o1, o2)-> {
		return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < 3; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= 100; i++)
			counter[i][0] = i;
		
		int time = 0;
		
		while (true) {
//			debug(time);
			if (arr[r][c] == k || ++time > 100)
				break;
			
			if (rSize >= cSize)
				R();
			else
				C();
		}
		
		if (time > 100)
			time = -1;
		
		System.out.println(time);
	}
	
	public static void R() {
		int maxSize = 0;
		
		for (int r = 0; r < rSize; r++) {
			for (int c = 0; c < cSize; c++) {
				counter[arr[r][c]][1]++;
			}
			
			for (int n = 1; n <= 100; n++) {
				if (counter[n][1] > 0)
					pQueue.offer(counter[n]);
			}
			
			int size = pQueue.size() * 2;
			if (size > maxSize)
				maxSize = size;
			
			int i = 0;
			while (!pQueue.isEmpty() && i < 100) {
				int[] state = pQueue.poll();
				
				arr[r][i++] = state[0];
				arr[r][i++] = state[1];
				
				state[1] = 0;
			}
			
			if (size > 100) {
				maxSize = 100;
				pQueue.clear();
			}
			
			for (int c = size; c < cSize; c++) {
				arr[r][c] = 0;
			}
		}
		
		cSize = maxSize;
	}
	
	public static void C() {
		int maxSize = 0;
		
		for (int c = 0; c < cSize; c++) {
			for (int r = 0; r < rSize; r++) {
				counter[arr[r][c]][1]++;
			}
			
			for (int n = 1; n <= 100; n++) {
				if (counter[n][1] > 0)
					pQueue.offer(counter[n]);
			}
			
			int size = pQueue.size() * 2;
			if (size > maxSize)
				maxSize = size;
			
			int i = 0;
			while (!pQueue.isEmpty() && i < 100) {
				int[] state = pQueue.poll();
				
				arr[i++][c] = state[0];
				arr[i++][c] = state[1];
				
				state[1] = 0;
			}
			
			if (size > 100) {
				maxSize = 100;
				pQueue.clear();
			}
			
			for (int r = size; r < rSize; r++) {
				arr[r][c] = 0;
			}
		}
		
		rSize = maxSize;
	}
	
	public static void debug(int time) {
		System.out.printf("-----time: %-3d-----\n", time);
		for (int r = 0; r < rSize; r++) {
			for (int c = 0; c < cSize; c++)
				System.out.printf("%-3d ", arr[r][c]);
			System.out.println();
		}
	}
	
}
