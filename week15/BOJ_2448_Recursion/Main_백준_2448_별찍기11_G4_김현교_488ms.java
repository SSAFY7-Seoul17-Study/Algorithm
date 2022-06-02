package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_2448_별찍기11_G4_김현교_488ms {

	static char[][] m;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int width = 2 * n - 1;
		int halfIdx = width / 2;

		m = new char[n][width];
		for (int i = 0; i < n; i++)
			Arrays.fill(m[i], ' ');

		markStar(n, 0, halfIdx);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < width; j++)
				sb.append(m[i][j]);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}// end main

	static void markStar(int n, int l, int r) {
		if (n == 3) {
			m[l][r] = '*';
			m[l + 1][r - 1] = '*';
			m[l + 1][r + 1] = '*';

			m[l + 2][r + 2] = '*';
			m[l + 2][r + 1] = '*';
			m[l + 2][r + 0] = '*';
			m[l + 2][r - 1] = '*';
			m[l + 2][r - 2] = '*';
			return;
		}

		int half = n / 2;
		markStar(half, l, r);
		markStar(half, l + (n / 2), r - half);
		markStar(half, l + (n / 2), r + half);
	}
}
