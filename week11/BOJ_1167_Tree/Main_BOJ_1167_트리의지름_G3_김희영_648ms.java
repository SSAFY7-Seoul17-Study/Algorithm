package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1167_트리의지름_G3_김희영_648ms {

	private static boolean[] v;
	private static int max = 0;
	private static int far;
	private static List<int[]>[] tree;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		tree = new List[V + 1];
		v = new boolean[V + 1];

		for (int i = 0; i < V; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int v = Integer.parseInt(st.nextToken());
			tree[v] = new LinkedList<int[]>();

			while (true) {
				int a = Integer.parseInt(st.nextToken());
				if (a == -1)
					break;
				int b = Integer.parseInt(st.nextToken());
				tree[v].add(new int[] { a, b });
			}
		}

		dfs(1, 0);
		Arrays.fill(v, false);
		dfs(far, 0);

		System.out.println(max);
	} // end of main

	private static void dfs(int node, int sum) {
		v[node] = true;
		boolean notMove = true;

		for (int[] arr : tree[node]) {
			if (!v[arr[0]]) {
				notMove = false;
				dfs(arr[0], sum + arr[1]);
			}
		}

		if (notMove) {
			if (sum > max) {
				max = sum;
				far = node;
			}
			return;
		}
	}

} // end of class
