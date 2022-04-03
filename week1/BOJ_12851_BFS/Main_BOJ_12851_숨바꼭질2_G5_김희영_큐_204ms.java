package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_12851_숨바꼭질2_G5_김희영_큐_204ms {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 수빈이 초기 위치
		int K = sc.nextInt(); // 동생 위치
		boolean[] v = new boolean[100001];

		Queue<int[]> q = new LinkedList<int[]>();

		int sec = -1; // 동생을 찾는 가장 빠른 시간
		int cnt = 0; // 방법의 수

		q.offer(new int[] { N, 0 });
		boolean notYet = true; // 동생을 찾기 전인지

		while (notYet) {
			sec++; // 너비 증가
			int len = q.size();
			for (int i = 0; i < len; i++) { // 큐 사이즈만큼
				int x = q.peek()[0]; // 현재 값
				int flag = q.poll()[1]; // 이전 값에서 -1했는지 +1했는지
				v[x] = true; // 방문처리
				// 방문 처리를 하는 이유 : 같은 숫자를 방문하는 순간 K까지 걸리는 시간이 똑같아지기 때문
				// 큐에 넣을 때 방문처리하지 않고 나올 때 방문처리 함
				// 이유는 같은 숫자를 다음 너비에서 방문하는 경우만 체크하면 되고
				// 넣을 때 방문 처리를 한다면 다른 루트지만 같은 숫자를 방문하는 경우를 카운트할 수 없기 때문

				if (x == K) { // 동생을 찾은 경우
					cnt++;
					notYet = false;
				}

				if (notYet) { // 아직 동생을 찾지 못했으면 큐에 계속 추가
					// 1을 더한 경우는 -1을 하면 원상복귀이므로 진행 X
					if (flag != 1 && x > 0) { // 뺐을 때 K의 범위 내인지 체크
						int tmp = x - 1;
						if (!v[tmp]) { // 방문 전인 경우
							q.offer(new int[] { tmp, -1 });
						}
					}
					if (K > x) { // K가 x보다 작으면 -1 연산만 필요
						// 1을 뺀 경우는 +1을 하면 원상복귀이므로 진행 X
						if (flag != -1 && x < 100000) { // 더했을 때 K의 범위 내인지 체크
							int tmp = x + 1;
							if (!v[tmp]) { // 방문 전인 경우
								q.offer(new int[] { tmp, 1 });
							}
						}
						if (x <= 50000) { // *2 했을 때 K의 범위 내인지 체크
							int tmp = x << 1;
							if (!v[tmp]) { // 방문 전인 경우
								q.offer(new int[] { tmp, 0 });
							}
						}
					}
				}
			}
		}

		System.out.println(sec);
		System.out.println(cnt);

	} // end of main

}
