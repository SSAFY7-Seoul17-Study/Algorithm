package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_4195_친구네트워크_G2_김희영_628ms {

	private static int[] parent;
	private static int[] level;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 0; testCase < TC; testCase++) {

			int F = Integer.parseInt(br.readLine()); // F개의 관계

			parent = new int[F * 2];
			level = new int[F * 2];

			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
				level[i] = 1;
			}

			int idx = 0;
			Map<String, Integer> map = new HashMap<>();

			for (int f = 0; f < F; f++) {

				st = new StringTokenizer(br.readLine(), " ");
				String s1 = st.nextToken();
				String s2 = st.nextToken();

				if (!map.containsKey(s1))
					map.put(s1, idx++);

				if (!map.containsKey(s2))
					map.put(s2, idx++);

				sb.append(union(map.get(s1), map.get(s2)) + "\n");

			}
		} // end of for TC

		System.out.println(sb.toString());

	} // end of main

	public static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	public static int union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
			level[x] += level[y];
			level[y] = 1;
		}
		return level[x];
	}

} // end of class
