import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_����_17140_�������迭������_���4_������_260ms
 * 
 *  - ���� ��ü
 *    1) int[][] arr: ������ �迭
 *    2) int rSize: �迭�� �� ũ��
 *    3) int cSize: �迭�� �� ũ��
 *    4) int[][] counter: arr���� ���� ���� ������ ���� �迭
 *    5) PriorityQueue<int[]> pQueue: counter�� ���� �������� ������ ����
 *  
 *  - ���� ���?
 *    : �켱 ���� ť�� ���� ���� �����ϱ� ���ٴ�, 1���� ���ҵ�, 2���� ���ҵ�, ... ���� �����Ͽ� �����ϴ� ���� ������?
 */
public class Main_����_17140_�������迭������_���4_������_260ms {

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
