package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1654_랜선자르기_S2_김희영_148ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] arr = new long[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(arr);
		long l = 0, r = arr[K - 1];

		while (l <= r) {
			long mid = (l + r) / 2;
			if (mid == 0)
				mid = 1;
			int sum = 0;
			for (int i = 0; i < K; i++) {
				sum += arr[i] / mid;
			}

//			System.out.println(l + " " + mid + " " + r + " " + sum);

			if (sum >= N)
				l = mid + 1;
			else
				r = mid - 1;
		}

		System.out.println(l - 1);

	} // end of main

} // end of class
