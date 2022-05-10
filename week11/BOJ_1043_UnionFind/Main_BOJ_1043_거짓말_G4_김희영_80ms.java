package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 서로소 집합
 */
public class Main_BOJ_1043_거짓말_G4_김희영_80ms {

	private static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		int[] party = new int[M];

		st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			parents[Integer.parseInt(st.nextToken())] = 0;
		}

		for (int m = 0; m < M; m++) {

			st = new StringTokenizer(br.readLine(), " ");
			int P = Integer.parseInt(st.nextToken());

			party[m] = Integer.parseInt(st.nextToken()); // 파티에 처음으로 오는 사람
			int a = party[m];

			for (int p = 1; p < P; p++) {
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
				a = b;
			}
		}

		int ans = 0;

		for (int m = 0; m < M; m++) {
			if (findSet(party[m]) != 0)
				ans++;
		}

		System.out.println(ans);

	} // end of main

	// a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = findSet(parents[a]); // path compression
	}

	// a, b 두 집합 합치기
	public static boolean union(int a, int b) {

		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		if (aRoot < bRoot)
			parents[bRoot] = aRoot;
		else
			parents[aRoot] = bRoot;
		return true;

	}
} // end of class
