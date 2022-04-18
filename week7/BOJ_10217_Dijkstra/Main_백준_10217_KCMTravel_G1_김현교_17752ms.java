package ShortestPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * dp에 해당 공항의 번호와 
 *
 */

public class Main_백준_10217_KCMTravel_G1_김현교_17752ms {

	static int N, maxCost;
	static List<Flight> list[];
	private static int minTime;
	
	public static void main(String[] args) throws Exception {
		PriorityQueue<Flight> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			maxCost = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			list = new ArrayList[N + 1];
			while (K-- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				int delay = Integer.parseInt(st.nextToken());
				
				if (list[from] == null)
					list[from] = new ArrayList<Flight>(N);
				list[from].add(new Flight(to, cost, delay));
			}
			
			if (list[1] == null || maxCost == 0) {
				sb.append("Poor KCM\n");
				continue;
			}
			
			minTime = Integer.MAX_VALUE;
			long[][] dp = new long[N + 1][maxCost + 1];
			for (int i = 0; i <= N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			dp[1][0] = 0;
			pq.offer(new Flight(1, 0, 0));
			
			while (!pq.isEmpty()) {
				Flight cur = pq.poll();
				
				if (cur.no == N) {
					minTime = cur.time;
					break;
				}
				
				if (list[cur.no] == null)
					continue;
				
				for (Flight next : list[cur.no]) {
					int nextCost = cur.cost + next.cost;
					if (nextCost > maxCost)
						continue;
					
					int nextDelay = cur.time + next.time;
					if (dp[next.no][nextCost] > nextDelay) {
						dp[next.no][nextCost] = nextDelay;
						pq.offer(new Flight(next.no, nextCost, nextDelay));
					}
				}
			}
			
			pq.clear();
			sb.append(minTime == Integer.MAX_VALUE ? 
					"Poor KCM\n" : (minTime + "\n"));
		}//end iter
		
		System.out.print(sb.toString());
	}//end main
	
	static class Flight implements Comparable<Flight> {
		int no, cost, time;

		public Flight(int to, int cost, int delay) {
			this.no = to;
			this.cost = cost;
			this.time = delay;
		}
		
		@Override
		public int compareTo(Flight o) {
			if (time == o.time)
				return cost - o.cost;
			return time - o.time;
		}
	}
}//end class
/*
 * 가장 짧은 시간별로 다익스트라 수행
 * 
 * 그 시간이 M원 이하인지 확인하고 아니면 캔슬
 * 
 * dfs로 가자
 * 시작점에서 갈수있는 모든 경로로 dfs
 * Cost와 시간의 합산을 넘기면서 진행
 * 
 * 다익스트라는 모든 점을 한 선으로 잇는 것이 목적
 * 
 * 지금 문제는 end지점까지의 최소값을 찾는 문제
 * 
 * 경유했을 때 시간과 end까지의 시간 비교
 * 
 * times부분이 문제임. 현재 시간은 좀 늦어도 최소 시간이 될 수 있음
 * 
 * 1~N까지 1번 노드에서만 시작했을 때 거리를 잼
 * 
 * 
 * */

