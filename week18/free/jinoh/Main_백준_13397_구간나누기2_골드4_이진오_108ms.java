import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_13397_구간나누기2_골드4_이진오_108ms
 * 
 *  - 매개변수 탐색 (Parametric search)
 *    : Parameter = score (점수)
 *    : Condition = M (배열을 구간으로 나누었을 때, 구간의 개수)
 *    
 *      => Parameter를 기준으로 이분 탐색을 진행 s.t. fn(score) <= M && fn(score-1) > M 
 *      
 */
public class Main_백준_13397_구간나누기2_골드4_이진오_108ms {
	
	private static int[] arr;
	private static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(paramSearch(M));
	}
	
	/**
	 * 현재 1차원 배열의 점수를 전역 변수 maxScore에 저장
	 */
	public static void getMaxScore() {
		int max = arr[0];
		int min = arr[0];
		
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			if (val < min)
				min = val;
			if (val > max)
				max = val;
		}
		
		maxScore = max - min;
	}
	
	/**
	 * @param M
	 * @return score
	 */
	public static int paramSearch(int M) {
		// 현재 arr에서 최대 점수 계산
		getMaxScore();
		
		int lo = 0;
		int hi = maxScore;
		
		while (lo < hi) {
			int mid = (lo + hi) >> 1;
			
			int cnt = count(mid);
			
			if (cnt > M) { // 현재 점수가 불가능
				lo = mid + 1;
			}
			else if (count(mid-1) > M) {
				return mid;
			}
			else {
				hi = mid - 1;
			}
		}
		
		return lo;
	}
	
	/**
	 * @param score
	 * @return score를 배열 구간의 최대 점수로 갖는, 구간의 개수
	 */
	public static int count(int score) {
		int cnt = 1;
		
		int max = arr[0];
		int min = arr[0];
		int tempScore = 0;
		
		for (int i = 1; i < arr.length; i++) {
			int val = arr[i];
			
			if (val > max) {
				max = val;
				tempScore = max - min;
			}
			if (val < min) {
				min = val;
				tempScore = max - min;
			}
			
			if (tempScore > score) {
				cnt++;
				max = val;
				min = val;
				tempScore = 0;
			}
		}
		
		return cnt;
	}
	
}
