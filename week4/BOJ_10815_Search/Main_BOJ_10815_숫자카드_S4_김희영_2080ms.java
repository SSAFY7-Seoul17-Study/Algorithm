package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_10815_숫자카드_S4_김희영_2080ms {

	static class Card implements Comparable<Card> {
		int value;
		int location;

		Card(int v, int l) {
			this.value = v;
			this.location = l;
		}

		@Override
		public int compareTo(Card o) {
			return this.value - o.value;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		Card[] brr = new Card[M];
		int[] ans = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			brr[i] = new Card(Integer.parseInt(st.nextToken()), i);
		}

		Arrays.sort(arr);
		Arrays.sort(brr);

		int i = 0;

		for (int j = 0; j < M; j++) {
			int result = 0;
			boolean have = false;

			while (i < N && brr[j].value > arr[i])
				i++;

			if (i < N)
				if (brr[j].value == arr[i])
					result = 1;

			ans[brr[j].location] = result;
		}

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < M; j++) {
			sb.append(ans[j]).append(" ");
		}

		System.out.println(sb.toString());

	} // end of main

}
