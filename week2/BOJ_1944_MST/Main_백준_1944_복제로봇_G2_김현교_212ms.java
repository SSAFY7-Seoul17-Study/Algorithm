package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1944_복제로봇_G2_김현교_212ms {

	static int N, M, locationMax;
	static char[] m;
	static ArrayList<Edge> edgeList;
	static int[] vertexLoc, vertexNum, parent;
	
	static int[] d = {-100, 1, 100, -1};//상 우 하 좌
	
	public static void main(String[] args) throws Exception {
		
		//미로 입력 받으면서 정점 정보 저장하기
		getInput();
		//BFS로 정점 간 거리 구해서 간선리스트 만들기
		getEdgeListByBFS();
		//EdgeList를 이용한 MST시작
		MST();
	}//end main

	private static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		locationMax = 100 * N + N;//위치의 최대 정수값
		
		vertexLoc = new int[M + 1]; 	  //인덱스에 정점번호, 값으로 위치
		vertexNum = new int[locationMax]; //인덱스에 위치, 값으로 정점번호
		
		int keyNum = 1;//정점 번호 값
		m = new char[locationMax];//미로 저장 맵
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				int loc = (100 * i) + j;//위치 정보를 정수1개로 표현
				m[loc] = line.charAt(j);
				
				if (m[loc] == 'S') { //시작점은 0번으로 저장
					vertexLoc[0] = loc;//시작점은 정점번호 0번 고정
					vertexNum[loc] = 0;
				} else if (m[loc] == 'K') { //열쇠는 1부터 순차적으로 번호 부여
					vertexLoc[keyNum] = loc;
					vertexNum[loc] = keyNum++;
				}
			}
		}
	}

	private static void getEdgeListByBFS() {
		edgeList = new ArrayList<Edge>(); //간선리스트
		boolean[] visited = new boolean[locationMax]; //방문체크 배열
		Queue<Integer> q = new LinkedList<>(); //BFS큐 (위치값 저장)

		for (int start = 0; start < M; start++) { //0~M-1번에서부터 나머지 정점까지 거리 찾기
			q.add(vertexLoc[start]);
			//시작점의 열쇠와 연결된 간선은 BFS로 다 찾아질 것이므로
			//다음 BFS시 탐색이 안되도록 0으로 변경
			m[vertexLoc[start]] = '0';
			
			Arrays.fill(visited, false);
			visited[vertexLoc[start]] = true;
			
			int dist = 0;
			while (!q.isEmpty()) {
				int len = q.size();
				for (int i = 0; i < len; i++) {
					int loc = q.poll();
					
					if (m[loc] == 'K') { //열쇠 발견 시 간선리스트에 추가
						int to = vertexNum[loc];
						edgeList.add(new Edge(start, to, dist));
					}
					
					for (int j = 0; j < 4; j++) {
						int nextLoc = loc + d[j];
						if (visited[nextLoc] || m[nextLoc] == '1')
							continue;
						visited[nextLoc] = true;
						q.add(nextLoc);
					}
				}
				dist++;
			}
		}
	}
	
	private static void MST() {
		Collections.sort(edgeList);//거리 짧은 순으로 정렬
		
		makeSet();
		int move = 0;//로봇이 움직인 거리
		int linkCnt = 0;//간선 수
		for (Edge cur : edgeList) {
			if (union(cur.from, cur.to)) {
				move += cur.w;
				linkCnt++;
				if (linkCnt == M)
					break;
			}
		}
		System.out.println(linkCnt == M ? move : -1);
	}
	
	static void makeSet() {
		parent = new int[M + 1];
		for (int i = 0; i <= M; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = findSet(parent[n]);
	}
	
	static boolean union(int a, int b) {
		int ar = findSet(a);
		int br = findSet(b);
		if (ar == br)
			return false;
		parent[br] = ar;
		return true;
	}
	
	static class Edge implements Comparable<Edge> {
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
}//end class
