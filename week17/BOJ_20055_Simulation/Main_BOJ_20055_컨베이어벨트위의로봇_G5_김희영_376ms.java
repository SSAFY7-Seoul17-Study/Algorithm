package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어벨트위의로봇_G5_김희영_376ms {

	static class Robot {
		int loc;

		public Robot(int loc) {
			this.loc = loc;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N * 2];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N * 2; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0, zero = 0;
		int up = 0, down = N - 1;
		List<Robot> robots = new LinkedList<Robot>();

		while (zero < K) { // 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
			// 0. 단계 증가
			ans++;

			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			up--;
			if (up == -1)
				up = 2 * N - 1;

			down--;
			if (down == -1)
				down = 2 * N - 1;

			// 만약 내리는 위치에 도달한 로봇이 있으면 로봇 내림
			if (robots.size() > 0) {
				if (robots.get(0).loc == down)
					robots.remove(0);
			}

			// 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			// 만약 이동할 수 없다면 가만히 있는다.

			for (int i = 0; i < robots.size(); i++) {
				int l = robots.get(i).loc;
				int next = l + 1;
				if (l == 2 * N - 1)
					next = 0;
				if (arr[next] > 0) { // 내구도가 1 이상이고
					if (i == 0 || robots.get(i - 1).loc != next) { // 앞 자리가 비어있으면
						robots.get(i).loc = next;
						arr[next]--;

						if (arr[next] == 0)
							zero++;

						if (next == down) { // 만약 내리는 위치에 도달하면 로봇 내림
							robots.remove(i);
							i--;
						}
					}
				}
			}

			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (arr[up] > 0) {
				Robot robot = new Robot(up);
				robots.add(robot);
				arr[up]--;

				if (arr[up] == 0)
					zero++;
			}

		}

		System.out.println(ans);
	} // end of main

} // end of class
