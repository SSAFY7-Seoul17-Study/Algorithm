import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Main_백준_3273_두수의합_실버3_이진오_224ms
 * 
 * 1) HashMap - 344ms, 35352kb
 *   : 숫자 num의 개수를 헤아린다. 
 *   : num의 개수 * (x-num)의 값이 곧 (num, (x-num)) 쌍의 개수가 된다. 
 *     - 주의
 *       : num을 항상 (x-num)보다 작은 값으로 상정
 *       : num == x-num일 경우, num의 개수 * (num의 개수 - 1) / 2를 구해야 한다. 
 * 
 * 2) int[] - 224ms, 29948kb
 *   : 숫자 num의 개수를, counter[num]에 저장
 *   : 앞선 과정과 동일
 *     - 주의
 *       : num을 항상 (x-num)보다 작은 값으로 상정
 *       : x-num이 배열의 크기를 초과하지 않는가 판별
 */
public class Main_백준_3273_두수의합_실버3_이진오_224ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		hashMap();
		array();
	} // end of main
	
	public static void hashMap() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (counter.containsKey(num))
				counter.put(num, counter.get(num)+1);
			else
				counter.put(num, 1);
		}
		
		int x = Integer.parseInt(br.readLine());
		int answer = 0;
		for (Integer num : counter.keySet()) {
			// num이 x의 절반보다 크다면
			if (num > x>>1) continue;
			// num의 개수 / x-num의 개수
			int cnt1 = counter.get(num);
			int cnt2 = counter.containsKey(x-num) ? counter.get(num) : 0;
			// num == x - num일 경우
			if (num<<1 == x)
				answer += cnt1 * (cnt1 - 1) / 2;
			// num != x - num일 경우
			else
				answer += cnt1 * cnt2;
		}
		
		System.out.println(answer);
		
		br.close();
		return;
	} // end of hashMap
	
	public static void array() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] counter = new int[1000001];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			counter[num]++;
		}
		
		int x = Integer.parseInt(br.readLine());
		int answer = 0;
		for (int num = 1; num <= x/2; num++) {
			if (x - num > 1000000) continue;
			int cnt1 = counter[num];
			int cnt2 = counter[x - num];
			if (num == x - num)
				answer += cnt1 * (cnt1 - 1) / 2;
			else
				answer += cnt1 * cnt2;
		}
		
		System.out.println(answer);
		
		br.close();
		return;
	} // end of array
	
} // end of class
