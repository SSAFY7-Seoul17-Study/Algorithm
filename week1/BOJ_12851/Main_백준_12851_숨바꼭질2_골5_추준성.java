import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_12851_숨바꼭질2_골5_추준성 {

	/*
	 * 1. 2차원 BFS 탐색과 다를 바 없음
	 * 2. 단지, 수빈이가 이동할 수 있는 방향이 {2*X, X+1, X-1}로 정해진 것 뿐
	 * 3. 이동한 위치가 K가 될 때까지 반복
	 * 주의사항1 : 가장 빠른 시간으로 수빈이가 동생을 찾는 "방법의 수" 또한 구해야 하므로, 최소 시간을 구했다고 해서 곧바로 종료하지 못함
	 * 주의사항2 : 로직상, 현재위치의 2배가 될 수 있으므로, visited 배열의 크기와, current의 범위를 200000을 넘지 않게끔 설정해야함
	 * 중요!! : 동일한 시간(너비) 사이의 관계라면, 방문했던 곳을 또 방문할 수 있게끔 만들어야 함. => queue에 offer할 때 방문처리 하는 게 아니라, poll할 때 방문처리 해야 함  
	 */
	
	/*
	 * 백트래킹 적용시 : 192ms 37552KB
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 수빈이의 위치가 동생보다 크거나 같으면, 왼쪽으로 걷는 경우 한 가지만 존재
		if(N >= K) {
			System.out.println(N-K);
			System.out.println(1);
			return;
		} else {
			bfs(N,K);
		}
		
		System.out.println(min);
		System.out.println(cnt);
	} // end of main
	
	private static int cnt;
	private static int min;
	private static int time;
	
	public static void bfs(int N, int K) {
		LinkedList<Integer> queue = new LinkedList<>();
		time = 0;
		cnt = 0; // 방법의 수
		min = Integer.MAX_VALUE;
		int current;
		boolean[] visited = new boolean[100001]; // 애초에 100000을 넘어가면 손해이므로, queue에 담을 때 이 값을 넘지 않는 선에서 탐색 (항상 경계값을 신경 쓰면서 잘 따져보고 체크해야 함)
		
		// 현재 수빈이의 위치 큐에 담기
		queue.offer(N); // 위치
		
		// bfs 탐색
		while(!queue.isEmpty()) {
			int qsize = queue.size();
			while(qsize-- > 0) {
				current = queue.poll();
				visited[current] = true; // queue에서 뽑을 때 방문처리
				
				if(current == K) { // 현재 위치가 K이면 최소 시간 갱신
					if(time == min) cnt++; 
					if(time < min) {
						min = time;
						cnt = 1;
					}
				}
				
				// 세 방향(경우)으로 이동 가능
				int dx1 = current+1;
				int dx2 = current-1;
				int dx3 = 2*current;
				
				// 이동한 위치를 방문 안 했다면 queue에 담기
				if(dx1 <= 100000 && !visited[dx1] && time < min) queue.offer(dx1); // 갱신된 최소 시간보다 작아야 함 (백트래킹)
				if(dx2 >= 0 && !visited[dx2] && time < min) queue.offer(dx2); // 갱신된 최소 시간보다 작아야 함 (백트래킹)
				if(dx3 <= 100000 && !visited[dx3] && time < min) queue.offer(dx3); // 갱신된 최소 시간보다 작아야 함 (백트래킹)
			}
			time++;
		}
		
	} // end of method bfs
	
} // end of class
