package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_10816_숫자카드2_S4_김희영_824ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[N];
		int[] memo = new int[20000001];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
			memo[card[i]+10000000]++;
		}

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		int[] want = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			want[i] = Integer.parseInt(st.nextToken());
			sb.append(memo[want[i]+10000000]).append(" ");
		}
		System.out.println(sb.toString());

	} // end of main

} // end of class
