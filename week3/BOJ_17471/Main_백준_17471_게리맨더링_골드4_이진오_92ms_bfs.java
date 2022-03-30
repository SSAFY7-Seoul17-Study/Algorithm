import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_17471_게리맨더링_골드4_92ms
 * 
 * - 아이디어
 *   : 자료구조 - 인접리스트
 *   : 부분집합 - 선택된 지역(1) & 선택되지 않은 지역(2)
 *   : BFS - 선택된 지역이 연결되어 있는지 판별
 *   
 * - 최적화
 *   : bfs를 따로 호출하지 않고, 하나의 코드 블럭에서 진행 시 더 빨라짐 (80ms)
 *
 */
public class Main_백준_17471_게리맨더링_골드4_이진오_92ms_bfs {
	
	static class Node {
		int val;
		Node link;
		
		public Node(int val) {
			this.val = val;
			this.link = null;
		}
		
		public Node next() {
			return this.link;
		} // end of next
		
		public void add(int val) {
			Node node = new Node(val);
			node.link = this.link;
			this.link = node;
		} // end of add
	} // end of class Node

	static int N;
	static int diff;
	static int totalPopul;
	
	static int[] populs;
	static boolean[] visited;
	static Node[] adjList;
	private static boolean[] advocates;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 인접 리스트 초기화
		adjList = new Node[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new Node(i);
		}
		
		// 각 지역의 인구 & 전체 인구수
		totalPopul = 0;
		populs = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			int popul = Integer.parseInt(st.nextToken());
			populs[i] = popul;
			totalPopul += popul;
		}
		
		// 인접 리스트에 값 추가
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int P = Integer.parseInt(st.nextToken()); // 각 지역에 인접한 지역의 수
			for (int j = 0; j < P; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// true = 1지역, false = 2지역
		visited = new boolean[N+1];
		visited[1] = true;
		// 지지 정당을 True, False로 나타내기 위한 배열
		advocates = new boolean[N+1];
		
		// 각 지역구의 인구수 차이 초기화
		diff = Integer.MAX_VALUE;
		subset(2, 1, populs[1]);
		
		if (diff > totalPopul) diff = -1;
		
		System.out.println(diff);
	} // end of main
	
	public static void subset(int cnt, int state1, int populSum) {
		if (cnt == N+1) {
			// 현재까지 선택된 지역구들 간 인구수의 차이
			int state1Popul = populSum;
			int state2Popul = totalPopul - populSum;
			int tempDiff = Math.abs(state1Popul - state2Popul);
			
			// 현재까지 선택된 지역구들 간 인구수의 차이가, 지금까지의 최소 인구수 차이보다 작다면 현재의 선택이 가능한지 bfs를 통해 판별
			if (tempDiff < diff) {
				// state2에 속하는 지역의 개수
				int state2 = N - state1;
				
				// state1의 대표 지역, state2의 대표 지역 선정
				int state1Repr = 0;
				int state2Repr = 0;
				for (int i = 1; i <= N; i++) {
					if (visited[i]) {
						state1Repr = i;
						break;
					}
				}
				for (int i = 1; i <= N; i++) {
					if (!visited[i]) {
						state2Repr = i;
						break;
					}
				}
				
				// temp 배열에, 현재까지의 visited 배열을 복사, bfs
				System.arraycopy(visited, 0, advocates, 0, N+1);
				int cnt1 = bfs(state1Repr, advocates);
				
				System.arraycopy(visited, 0, advocates, 0, N+1);
				int cnt2 = bfs(state2Repr, advocates);
				
				// state1이랑 state2의 지역들 각각이 연결되어 있다면
				if (cnt1 == state1 && cnt2 == state2) {
					diff = tempDiff;
				}
			}
			return;
		} // end of 기저조건
		
		// 부분집합 코드 블럭
		
		// state1에 cnt번째 지역을 선택한 경우
		visited[cnt] = true;
		subset(cnt+1, state1+1, populSum+populs[cnt]);
		visited[cnt] = false;
		
		// state1에 cnt번째 지역을 선택하지 않는 경우
		subset(cnt+1, state1, populSum);	
	}
	
	public static int bfs(int stateRepr, boolean[] advocates) {
		if (stateRepr == 0) return -1;
		
		// 대표 지역의 지지 정당
		boolean flag = advocates[stateRepr];
		
		Queue<Integer> queue = new LinkedList<Integer>();
		advocates[stateRepr] = !flag;
		queue.offer(stateRepr);
		
		// 연결된 지지 정당의 수
		int cnt = 0;
		while(!queue.isEmpty()) {
			Node node = adjList[queue.poll()];
			cnt++;
			
			while((node = node.next()) != null) {
				// 만약, node가 대표 지역의 지지 정당과 동일한 정당을 지지한다면
				if (advocates[node.val] == flag) {
					advocates[node.val] = !flag;
					queue.offer(node.val);
				}
			}
		}
		
		return cnt;
	}
	
} // end of class
