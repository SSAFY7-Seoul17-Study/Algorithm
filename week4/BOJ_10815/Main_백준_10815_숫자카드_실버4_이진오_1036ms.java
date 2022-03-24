import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_10815_숫자카드_실버4_이진오_1036ms
 * 
 * - 아이디어
 *   : 이분 탐색(binary Search) 직접 구현 - 1036ms
 *   : 이분 탐색 Arrays.util 메서드 호출 - 1096ms
 *   : TreeSet - 1212ms
 *   : HashSet - 896ms
 * 
 */
public class Main_백준_10815_숫자카드_실버4_이진오_1036ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int x = Integer.parseInt(st.nextToken());
			sb.append(binarySearch(arr, x, 0, N)).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static int binarySearch(int[] arr, int x, int start, int end) {
		if (start == end)
			return 0;
		int mid = start + (end - start) / 2;
		if (x == arr[mid])
			return 1;
		else if (x < arr[mid])
			return binarySearch(arr, x, start, mid);
		else
			return binarySearch(arr, x, mid+1, end);
	} // end of binarySearch
	
} // end of class
