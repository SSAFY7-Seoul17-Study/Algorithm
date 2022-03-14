import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_1944_복제로봇_골2_추준성_232ms {
	/*
	 * 1. 시작정점(S)과 열쇠가 있는 정점(K)들을 하나도 빠짐없이(모든 열쇠를 찾아야하기 때문) 이었을 때, 총 이동거리(==간선 비용)가 최소가 돼야 함 => MST(최소신장트리) 
	 * 2. 정점대비 간선 수가 많으므로 PRIM 알고리즘이 선택 => BFS 활용해서 인접행렬 만들고, PRIM 알고리즘 적용 => 최소 총 이동거리 출력
	 */
	static class Node{
		int number, r, c;

		public Node(int number, int r, int c) {
			super();
			this.number = number;
			this.r = r;
			this.c = c;
		}
	}

	private static int N;
	private static int[] dr = {1,-1, 0, 0};
	private static int[] dc = {0, 0, 1,-1};
	private static int[][] adjMatrix;
	private static Node[] nodes;
	private static int M;
	private static char[][] map;
	private static int edgeCount;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		nodes = new Node[M+1]; // 시작 노드 포함해서 크기가 M+1
		// map 입력 받기
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 시작정점, 열쇠정점 찾아서 nodes 배열에 담기
		int start = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'S') nodes[0] = new Node(0, i, j);
				if(map[i][j] == 'K') nodes[++start] = new Node(start, i, j);
			}
		}
		
		// 각 정점을 시작점으로 탐색하면서 간선정보(각 정점간 이동거리 == 가중치)를 기반으로 인접행렬 만들기
		adjMatrix = new int[M+1][M+1];
		int[] minEdge = new int[M+1]; // 타 정점에서 자신으로의 간선비용 중 최소비용
		boolean[] visited = new boolean[M+1]; // 신장트리에 선택됐는지 여부
		
		// bfs 돌리기 (== 인접행렬 채우기)
		for (int i = 0; i < M+1; i++) {
			bfs(nodes[i]);
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		// Prim 알고리즘
		int result = 0; // MST 비용
		minEdge[0] = 0; // Prim 알고리즘 시작 정점
		edgeCount = M+1;
		
		for (int c = 0; c < M+1; c++) { // M+1개의 정점을 모두 연결
			// 신장트리에 연결되지 않은(==선택되지 않은) 정점들 중 가장 유리한 비용의 정점을 선택
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			boolean connected = false;
			for (int i = 0; i < M+1; i++) {
				if(!visited[i] && min > minEdge[i]) { // 선택되지 않은 정점들 중 가장 유리한 비용의 정점 선택 (해당 비용이 MST 비용에 누적됨)
					min = minEdge[i]; // 신장트리에 연결된 최소 비용 저장
					minVertex = i; // 신장트리에 연결된 정점 번호 저장
					connected = true;
				}
			}
			
			visited[minVertex] = true; // 신장트리 연결 처리
			result += min; // 선택된 최소 비용 저장
			if(connected) edgeCount--;
			
			// 신장트리에 연결되지 않은 정점들 중 현재 선택한(==신장트리에 추가된) 정점과 연결되어 있는 정점에 대하여 최소비용 갱신
			for (int i = 0; i < M+1; i++) {
				if(!visited[i] && adjMatrix[minVertex][i]!=0 && minEdge[i] > adjMatrix[minVertex][i]) { // 기존에 갖고 있던 최소비용보다 작으면 갱신
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
		if(edgeCount!=0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
		
	} // end of main
	
	// bfs에서 간선 정보 업데이트
	public static void bfs(Node startNode) {
		LinkedList<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.offer(new int[] {startNode.r, startNode.c, 0});
		visited[startNode.r][startNode.c] = true;
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int cr = current[0];
			int cc = current[1];
			
			// 어떤 열쇠 또는 시작정점을 만나면 인접행렬에 이동 거리 정보를 반영
			if(map[cr][cc] == 'S' || map[cr][cc] == 'K') {
				for (int i = 0; i < M+1; i++) {
					if(nodes[i].r == cr && nodes[i].c == cc) {
						adjMatrix[startNode.number][nodes[i].number] = adjMatrix[nodes[i].number][startNode.number] = current[2];
					}
				}
			}
				
			// 4방탐색
			for (int i = 0; i < 4; i++) {
				int nr = current[0] + dr[i];
				int nc = current[1] + dc[i];
				if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc]!='1') {
					queue.offer(new int[] {nr, nc, current[2]+1});
					visited[nr][nc] = true;
				}
			}
		}
		
	} // end of method bfs
	
} // end of class
