import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_2110_공유기설치_골드5_이진오_264ms
 * 
 *  - 매개변수 탐색 (Parametric search)
 *  
 *    0) 문제
 *      : 가장 인접한 두 공유기 사이의 거리를 최대로
 *        => 최적해 문제 (Optimal solution)
 *        => 결정 문제로 변환
 *   
 *    1) 풀이
 *      - 집 좌표 정렬: O(n logn)
 *      - 결정 조건 함수 구현: O(n)
 *      - 이분 탐색 구현: O(log x_max)
 *        => O(n (log n + log x_max))
 *    
 */
public class Main_백준_2110_공유기설치_골드5_이진오_264ms {
	
	private static int N;
	private static int C;
	private static int[] houses;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		houses = new int[N];
		
		for (int i = 0; i < N; i++)
			houses[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(houses);
		
		System.out.println(paramSearch());
	}
	
	public static boolean decide(int dist) {
		// 첫 번째 집에 공유기를 설치한다 
		int cnt = 1;
		int offset = houses[0];
		
		// dist 이상의 거리에 공유기를 설치할 수 있으면, 설치한다. 
		for (int i = 1; i < N; i++) {
			if (houses[i] - offset < dist)
				continue;
			cnt++;
			offset = houses[i];
		}
		
		return cnt >= C;
	}
	
	public static int paramSearch() {
		int lo = 1;
		int hi = houses[N-1] - houses[0];
		
		while (lo < hi) {
			int mid = (lo + hi) >> 1;
			
			if (!decide(mid)) {
				hi = mid - 1;
			}
			else if (!decide(mid+1)) {
				return mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		return lo;
	}
}
