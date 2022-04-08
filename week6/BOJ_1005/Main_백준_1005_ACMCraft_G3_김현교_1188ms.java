package TopologicalSort;

import java.io.BufferedReader;	
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상정렬로 노드 정렬 하는 문제
 * 
 * 동시에 작업을 하는 경우를 고려해주어야 한다
 * 
 * indegree가 0인 것을 찾을 때 그 건설시간이 최대인 것만 time누적에 더해준다.
 *	-> X
 *  -> indegree가 같은 타이밍에 0이 된 노드가 2개이고 그 2개에 각각 1개가 연결되어있는 경우 고려
 * 
 * [수정]
 * PQ를 이용하여서 먼저 끝난 건물에 연결된 것 추가해주기
 * 
 * */

public class Main_백준_1005_ACMCraft_G3_김현교_1188ms {
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Building> pq = new PriorityQueue<>();
		Queue<Building> q = new LinkedList<>();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] delay = new int[N + 1];
			List<Integer> link[] = new List[N + 1]; //인접리스트
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				delay[i] = Integer.parseInt(st.nextToken());
				link[i] = new ArrayList<Integer>();
			}
			
			int[] inDegree = new int[N + 1];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				link[from].add(to);
				inDegree[to]++;
			}
			int W = Integer.parseInt(br.readLine());
			
			int time = -1;
			for (int j = 1; j <= N; j++) {
				if (inDegree[j] == 0) {
					if (j == W) {
						time = delay[W];
						break;
					}
					pq.offer(new Building(j, delay[j]));
				}
			}
			
			if (time == -1) {
				time = 0;
				
out:			while (!pq.isEmpty()) {
					int minTime = -1;
					
					int size = pq.size();
					while (size-- > 0) {
						
						Building del = pq.poll();
						if (minTime == -1) {
							minTime = del.time;
							time += minTime;
						}
						
						del.time -= minTime;
						if (del.time > 0) {
							q.offer(del);
							continue;
						}
						
						for (Integer to : link[del.num]) {
							if (--inDegree[to] == 0) {
								if (to == W) {
									time += delay[W];
									break out;
								}
								q.offer(new Building(to, delay[to]));
							}
						}
					}
					
					while (!q.isEmpty())
						pq.offer(q.poll());
				}
			}
			pq.clear();
			q.clear();
			
			sb.append(time).append("\n");
		}
		
		System.out.print(sb.toString());
	}//end main
	
	static class Building implements Comparable<Building> {
		int num;
		int time;
		
		public Building(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
		
		@Override
		public int compareTo(Building o) {
			return time - o.time;
		}
	}
}

/*
 * indeg 0인거 pq에 넣음
 * pq크기에 맞춰 하나씩 뺌
 * 처음 나온거는 time에 누적
 * 그 이후에 나온거는 처음 시간만큼 빼주고 다시 넣음
 * link연결 끊어주면서 indeg 0이면 일반큐에 넣어줌
 * 
 * 다 봤으면 일반큐에 있는거 pq에 넣어줌
1
10 11
10 20 30 40 50 60 70 80 90 100
1 2
2 3
3 6
6 9
5 4
4 7
7 8 
8 9
4 9
10 7
4 3
9

ans : 340
 * */

