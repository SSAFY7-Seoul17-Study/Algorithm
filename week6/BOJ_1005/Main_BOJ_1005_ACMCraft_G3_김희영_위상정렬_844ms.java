package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상정렬
 * 노드에 도달하기까지 지연되는 시간을 저장하는 배열 delay를 이용
 * 내 다음 노드에 delay 값이 나의 delay + time 값보다 작으면 갱신
 */
public class Main_BOJ_1005_ACMCraft_G3_김희영_위상정렬_844ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int testCase = 0; testCase < TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] time = new int[N + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			int[] arr = new int[N + 1];
			List<Integer>[] list = new LinkedList[N + 1];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				arr[to]++; // 진입간선 수

				if (list[from] == null) // 인접리스트
					list[from] = new LinkedList<Integer>();
				list[from].add(to);
			}
			int W = Integer.parseInt(br.readLine());

			int[] delay = new int[N + 1];
			Queue<Integer> q = new LinkedList<Integer>();

			for (int i = 1; i < N + 1; i++) {
				if (arr[i] == 0)
					q.offer(i);
			}

			while (q.size() > 0) {
				int from = q.poll();
				delay[from] += time[from];

				if (from == W)
					break;

				if (list[from] != null)
					for (Integer to : list[from]) {
						if (delay[to] < delay[from])
							delay[to] = delay[from];

						arr[to]--;
						if (arr[to] == 0)
							q.offer(to);
					}
			}

			sb.append(delay[W]).append("\n");
		}

		System.out.println(sb);

	} // end of main

}