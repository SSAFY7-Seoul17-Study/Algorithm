package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_13397_구간나누기2_G4_김희영_108ms {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		int l = 0, r = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] > r)
				r = arr[i];
		}

		int mid = 0;
		while (l <= r) {
			mid = (l + r) / 2;
			int sum = 0;
			for (int i = 0; i < N;) {
				int max = arr[i], min = arr[i];
				while (max - min <= mid) {
					if (++i == N)
						break;
					if (arr[i] > max)
						max = arr[i];
					if (arr[i] < min)
						min = arr[i];
				}
				sum++;
				if (i == N)
					break;
			}
			if (sum > M)
				l = mid + 1;
			else
				r = mid - 1;
		}

		System.out.println(l);

	} // end of main

} // end of class
