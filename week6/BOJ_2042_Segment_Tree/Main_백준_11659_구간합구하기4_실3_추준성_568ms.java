import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_11659_구간합구하기4_실3_추준성_568ms {
	/*
	 * - 단순 합 구하면, 연산이 100000번이 가능하므로, 시간복잡도 O(N^2)라서 터짐
	 * - 아이디어 : 입력 받을 때 그 수까지의 합(누적합)을 저장해두고, 구간 합을 구할 땐 빼기 연산을 통해 쉽게 구함
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		// 입력과 동시에 미리 해당 수까지의 합을 저장
		for (int i = 1; i <= N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		// 저장한 합의 차를 이용해 구간 합을 구하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(arr[end] - arr[start-1]).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

} // end of class
