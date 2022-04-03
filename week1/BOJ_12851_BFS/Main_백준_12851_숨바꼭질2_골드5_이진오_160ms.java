import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_12851_숨바꼭질2_골드5_160ms
 * 
 *  - 문제점 피드백
 *    : 문제를 보고, BFS임을 알아차리지 못한 점
 *    
 *  - 아이디어
 *    (0) BFS 구현
 *        : 현재 레벨의 좌표 x1, x2에서 다음 레벨의 좌표 y1에 접근이 가능할 때
 *        : routes[y1] += routes[x1];
 *        : routes[y1] += routes[x2];
 *          - 이를 구현하기 위해, int[]형 배열 routes 선언
 *          - routes 배열의 각 원소를 0으로 초기화
 *          - y1에 여러번 접근해야 하며, 큐에는 한 번만 load돼야 한다. 
 *            => queue 하나로는 힘들 것으로 보임
 *        
 *    (1) LinkedList를 이용한 BFS 구현 - 520ms
 *        : 메모리를 덜 차지하지만, 그만큼 시간이 더 걸린다.
 *        : LinkedList는 각 원소로 접근할 때 O(1)의 시간복잡도를 보장받지 못한다. 
 *        
 *    (2) Queue 두개를 이용한 BFS 구현 - 160ms
 *        : 메모리를 더 차지하지만, 시간이 절약된다. 
 *        : 큐의 offer(), poll()은 O(1)의 시간에 가능하기 때문에, 시간이 절약된다. 
 * 
 */
public class Main_백준_12851_숨바꼭질2_골드5_이진오_160ms {
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 0 <= N <= 100,000
		int K = Integer.parseInt(st.nextToken()); // 0 <= K <= 100,000
		int max = Math.max(N+1, Math.min(100001, K*2)); // BFS시, 가능한 좌표의 최댓값
		
		int[] routes = new int[max]; // N에서 시작해서 i번 좌표로 가는 경로의 경우의 수
		boolean[] visited = new boolean[max];
		
		// BFS를 돌리기 위한 queue, 각 레벨을 관리하기위한 level
		Queue<Integer> queue = new LinkedList<Integer>();
		Queue<Integer> level = new LinkedList<Integer>();
		queue.offer(N);
		visited[N] = true;
		routes[N] = 1;
		
		int cnt = 0;
		int time = 0;
		
		while(!queue.isEmpty()) {
			
			// 다음 레벨 좌표들의 경로 수 update
			while(!queue.isEmpty()) {
				int pre = queue.poll();
				int route = routes[pre];
				level.offer(pre);
				
				if (pre == K) cnt += route;
				
				// 현재 레벨에서, 다음 레벨로의 첫 방문일 경우 가능한 모든 경로의 수를 추가
				if (pre-1 >= 0 && !visited[pre-1]) {
					routes[pre-1] += route;
				}
				if (pre+1 <max && !visited[pre+1]) {
					routes[pre+1] += route;
				}
				if (pre*2 <max && !visited[pre*2]) {
					routes[pre*2] += route;
				}
			}
			// K 탐색 완료
			if (cnt > 0) break;

			// 다음 레벨 좌표들 방문 처리 및 큐에 추가
			while(!level.isEmpty()) {
				int pre = level.poll();
				
				if (pre-1 >= 0 && !visited[pre-1]) {
					visited[pre-1] = true;
					queue.offer(pre-1);
				}
				if (pre+1 <max && !visited[pre+1]) {
					visited[pre+1] = true;
					queue.offer(pre+1);
				}
				if (pre*2 <max && !visited[pre*2]) {
					visited[pre*2] = true;
					queue.offer(pre*2);
				}
			}
			
			time++;
		} // end of while queue
		
		System.out.println(time);
		System.out.println(cnt);
		
	} // end of main
	
} // end of class
