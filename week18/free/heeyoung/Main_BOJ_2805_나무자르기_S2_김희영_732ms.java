package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2805_나무자르기_S2_김희영_732ms {

	private static int N;
	private static long[] trees;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		trees = new long[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(trees);
		long[] srr = new long[N];
		srr[N - 1] = trees[N - 1];
		for (int i = N - 2; i > -1; i--) {
			srr[i] = srr[i + 1] + trees[i];
		}

		long l = 0, r = trees[N - 1]; // r = 가장 높은 나무
		long mid = 0;

		while (l < r) {
			mid = (l + r) / 2;
			int idx = bs(mid);

			long sum = srr[idx];
			sum -= (N - idx) * mid;

			if (M <= sum)
				l = mid + 1;
			else
				r = mid;
		}
		System.out.println(l - 1);

	} // end of main

	private static int bs(long h) {
		int l = 0, r = N - 1;
		while (l < r) {
			int mid = (l + r) / 2;
			if (h == trees[mid])
				return mid;
			else if (trees[mid] < h)
				l = mid + 1;
			else
				r = mid;
		}

		return l;
	}

} // end of class
