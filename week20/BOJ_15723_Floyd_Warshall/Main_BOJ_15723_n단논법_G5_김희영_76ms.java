package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15723_n단논법_G5_김희영_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] arr = new boolean[26][26];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int to = st.nextToken().charAt(0) - 'a';

			arr[from][to] = true;
		}

		for (int k = 0; k < 26; k++) {
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (arr[i][k] && arr[k][j])
						arr[i][j] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int from = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int to = st.nextToken().charAt(0) - 'a';

			if (arr[from][to])
				sb.append("T\n");
			else
				sb.append("F\n");
		}

		System.out.println(sb.toString());

	} // end of main

} // end of class
