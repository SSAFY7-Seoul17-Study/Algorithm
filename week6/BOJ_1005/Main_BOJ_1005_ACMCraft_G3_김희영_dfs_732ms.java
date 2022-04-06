package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 위상정렬의 시작점을 W로 생각하고 역순으로 순서찾기
 * DFS 사용
 * memoization 사용
 */
public class Main_BOJ_1005_ACMCraft_G3_김희영_dfs_732ms {

	private static int[] time;
	private static List<Integer>[] list;
	private static int[] delay;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int testCase = 0; testCase < TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			time = new int[N + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			list = new ArrayList[N + 1];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				if (list[to] == null) // 인접리스트(역순 to->from)
					list[to] = new ArrayList<Integer>();
				list[to].add(from);
			}
			int W = Integer.parseInt(br.readLine());

			delay = new int[N + 1];
			Arrays.fill(delay, -1);

			sb.append(dfs(W)).append("\n");
		}

		System.out.println(sb);

	} // end of main

	private static int dfs(int w) {
		int max = 0;

		if (delay[w] != -1)
			return delay[w];

		if (list[w] == null) {
			return delay[w] = time[w];
		}

		for (int n : list[w]) {
			int time = dfs(n);
			if (time > max)
				max = time;
		}

		return delay[w] = max + time[w];
	}

}