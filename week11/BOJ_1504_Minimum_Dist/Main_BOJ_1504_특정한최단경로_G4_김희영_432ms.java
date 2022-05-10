package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1-A-B-N
 * 1-B-A-N
 * 중 최단 경로 구하기
 * A-B = B-A
 */
public class Main_BOJ_1504_특정한최단경로_G4_김희영_432ms {

	private static int[][] adj;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		adj = new int[N + 1][N + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = adj[b][a] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int wayAB = dijkstra(A, B);

		int way1 = dijkstra(1, A) + wayAB + dijkstra(B, N);
		int way2 = dijkstra(1, B) + wayAB + dijkstra(A, N);

		System.out.println(Math.min(way1, way2));

	} // end of main

	private static int dijkstra(int s, int e) {
		int[] distance = new int[N + 1]; // 출발지에서 자신으로 오는 최소비용
		boolean[] visited = new boolean[N + 1]; // 최소비용 확정여부

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0; // 시작점 0으로

		for (int i = 1; i <= N; i++) {
			// 단계 1 : 최소비용이 확정되지 않은 정점 중 최소비용의 정점 선택
			int min = Integer.MAX_VALUE, current = 0;

			for (int j = 1; j <= N; j++) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}

			visited[current] = true;
			if (current == e) // current가 도착지라면 끝냄
				break;

			// 단계2 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른 정점의 최소 비용을 고려
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && adj[current][j] != 0 && distance[j] > distance[current] + adj[current][j]) {
					distance[j] = distance[current] + adj[current][j];
				}

			}
		}

		if (distance[e] == Integer.MAX_VALUE) {
			System.out.println(-1);
			System.exit(0);
		}

		return distance[e];
	}

} // end of class
