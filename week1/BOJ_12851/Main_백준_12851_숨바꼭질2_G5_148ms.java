package DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_12851_숨바꼭질2_G5_148ms {
	/* 그냥 BFS돌리면 시간초과남
	 * 
	 * 여러 방법으로 같은 레벨에서 같은 숫자값이 된 경우의 카운트를 더해주고 그 값을 가지고 다님
	 * 
	 * 다음 레벨에서 또 다른 방법으로 같은 숫자를 만나면 그 두 경우의 카운트를 더해줌
	 * 
	 * 방문체크는 같은레벨의 같은 숫자는 거르지 않도록 조금 뒤에 해줌
	 * 
	 * BFS
	 * 	한 레벨에 큐에 든 숫자 다 꺼내면서 카운트들을 다 더해줌
	 * 	[0]은 num, [1]은 cnt
	 * 	꺼낼때마다 count[num] += cnt 를 수행
	 * 	
	 * 	만약 count[K] > 0이면 종료
	 * 
	 * 	count[num]이 0보다 크면 visit[num] 참으로 바꿔줌
	 * `큐에 3방향으로 연산 수행 후 넣어줌(이미 방문했으면 넣지 않는 방법으로 가지치기)
	 * 
	 * */
	public static final int MAX = 100001;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[] v = new boolean[MAX];

		int[] count = new int[MAX];

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N, 1});
		Queue<Integer> tempQueue = new LinkedList<>();
		
		int caseCnt = 0;
		int time = 0;
		while (!q.isEmpty()) {
			int len = q.size();
			for (int i = 0; i < len; i++) {
				int[] poll = q.poll();
				int num = poll[0];
				int cnt = poll[1];

				count[num] += cnt;
				tempQueue.offer(num);
			}
			
			if (count[K] > 0) {
				caseCnt = count[K];
				break;
			}
			
			while (!tempQueue.isEmpty()) {
				int i = tempQueue.poll();
				if (!v[i]) {
					v[i] = true;
					if (i * 2 < MAX && !v[i * 2])
						q.offer(new int[] {i * 2, count[i]});
					if (i - 1 >= 0 && !v[i - 1])
						q.offer(new int[] {i - 1, count[i]});
					if (i + 1 < MAX && !v[i + 1])
						q.offer(new int[] {i + 1, count[i]});
					count[i] = 0;
				}
			}
			time++;
		}
		System.out.println(time + "\n" + caseCnt);
	}//end main
}//end class
