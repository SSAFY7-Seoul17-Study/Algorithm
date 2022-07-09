import java.io.*;
import java.util.*;

/**
 * Main_백준_10282_해킹_골드4_이진오_756ms
 * 
 *  0) 문제
 *    : (a, b, s)는 b에서 a로 향하는 간선을 나타낸다. 
 *      => 그래프의 간선과, 의존성 관계의 화살표는 서로 반대
 *    : 다익스트라로 풀어서, 가장 오래 걸리는 컴퓨터가 몇 초 걸리는지 계산
 *      => 다익스트라로 풀어야, 해당 초가 최단 경로임이 보장
 *  
 *  1) 자료구조
 *    - class State (int v, int d)
 *      : 하나의 정점과 가중치를 저장하는 상태 클래스
 *    - int[] dist
 *      : start 정점으로부터 최단 거리를 저장하는 배열
 *    - ArrayList<State>[] adjList
 *      : 그래프를 저장하는 인접 리스트
 *    - int count, total, start
 *      : 컴퓨터의 대수, 총 걸린 시간, 시작 정점
 *  
 *  2) 풀이
 *    - 다익스트라 + 대수, 총 걸린 시간 계산하기
 *      : count, total은 꺼내면서 계산한다.
 *        => 최단 경로임이 보장된 State에 대해서만 진행해야 한다. 
 * 
 */
public class Main_백준_10282_해킹_골드4_이진오_756ms {

	static final int MAX = 987654321;
	
	static class State implements Comparable<State> {
		int v;
		int d;
		
		public State(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(State o) {
			return this.d - o.d;
		}
	}
	
	static int[] dist;
	static ArrayList<State>[] adjList;
	
	static int count;
	static int total;
	
	static int N, M;
	static int start;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			init(br);
			dijkstra();
			sb.append(count).append(" ").append(total).append("\n");
			
			clear();
		}
		
		System.out.print(sb.toString());
	}
	
	public static void clear() {
        adjList = null;
        dist = null;
	}
	
	@SuppressWarnings("unchecked")
	public static void init(BufferedReader br) throws IOException {
		count = 0;
		total = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		
		adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<State>();
		
		dist = new int[N+1];
		Arrays.fill(dist, MAX);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			adjList[v2].add(new State(v1, d));
		}
	}
	
	public static void dijkstra() {
		PriorityQueue<State> pQueue = new PriorityQueue<State>();
		
		pQueue.add(new State(start, 0));
		dist[start] = 0;
		
		while (!pQueue.isEmpty()) {
			State s = pQueue.poll();
			
			int v1 = s.v;
			int d1 = s.d;
			
			if (d1 == dist[v1]) {
				count++;
				total = d1;
			}
			
			for (State to : adjList[s.v]) {
				int v2 = to.v;
				int d2 = to.d;
				if (d1 + d2 < dist[v2]) {
					pQueue.offer(new State(v2, d1 + d2));
					dist[v2] = d1 + d2;
				}
			}
		}
	}
	
}
