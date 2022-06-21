import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_1253_좋다_골드4_이진오_236ms
 * 
 *  - 이분 탐색 구현
 *    : 수열 seq[]에 대하여, 
 *      seq[i] == seq[j] + seq[k] && (i, j, k)는 모두 다르다
 *        => 위 표현식을 만족하는 서로 다른 i의 개수 
 *    
 *  - 로직
 *    : target = seq[i] - seq[j];
 *      int k = binSearch(target);
 *        => target을 탐색할 때 이분 탐색을 사용
 *    : k != i, k != j를 만족해야 함
 *        => 
 */
public class Main_백준_1253_좋다_골드4_이진오_236ms {
	
	private static int N;
	private static int[] seq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		seq = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(seq);
		
		int cnt = 0;
		for (int i = 0; i < N; i++) { // 탐색하고자 하는 원소 seq[i]
			for (int p1 = 0; p1 < N; p1++) { // seq[i] = seq[p1] + ...에 가능한 p1
				if (i == p1)
					continue;
				
				// seq[i] == seq[p1] + seq[p2]
				int target = seq[i] - seq[p1];
				int p2 = binSearch(target);
				
				// seq[p2] == seq[i] - seq[p1]을 만족하는 p2가 존재하지 않을 때
				if (p2 == -1)
					continue;
				
				// p2가 i 혹은 p1과 같아서 사용할 수 없을 때 -> seq[p2]와 동일한 값을 갖는 다른 원소를 탐색
				if (p2 == i || p2 == p1) {
					if (find(i, p1, p2)) {
						cnt++;
						break;
					}
				}
				else {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	/**
	 * @param target
	 * @return seq 내 target의 위치, 존재하지 않는다면 -1
	 */
	public static int binSearch(int target) {
		int lo = 0;
		int hi = N-1;
		
		while (lo < hi) {
			int mid = (lo + hi) >> 1;
			if (seq[mid] < target)
				lo = mid+1;
			else if (seq[mid] > target)
				hi = mid-1;
			else
				return mid;
		}
		
		if (seq[lo] == target)
			return lo;
		return -1;
	}
	
	/**
	 * @param i
	 * @param p1
	 * @param p2
	 * @return seq[i] = seq[p1] + seq[p2]를 만족할 때, p2위치에 가능한 다른 p 값이 존재하는지 여부
	 */
	public static boolean find(int i, int p1, int p2) {
		int val = seq[p2];
		
		// p2보다 큰 ptemp에 대해, seq[p2] == seq[ptemp] && (ptemp != i && ptemp != p1)인 ptemp를 탐색
		int ptemp = p2 + 1;
		while (ptemp < N && val == seq[ptemp]) {
			if (ptemp != i && ptemp != p1)
				return true;
			
			ptemp++;
		}
		
		ptemp = p2 - 1;
		while (ptemp >= 0 && val == seq[ptemp]) {
			if (ptemp != i && ptemp != p1)
				return true;
				
			ptemp--;
		}
				
		return false;
	}
}
