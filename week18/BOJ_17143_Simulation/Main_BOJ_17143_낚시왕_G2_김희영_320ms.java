package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕_G2_김희영_320ms {

	public static class Shark {
		int r, c, s, d, z;
		boolean isAlive;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s; // 속력
			this.d = d; // 방향
			this.z = z; // 크기
			isAlive = true;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] space = new int[R + 1][C + 1];

		int M = Integer.parseInt(st.nextToken());
		Shark[] sharks = new Shark[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			sharks[i] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			space[sharks[i].r][sharks[i].c] = i;
		}

		int sum = 0; // 낚시왕이 잡은 상어 크기의 합

		for (int i = 1; i <= C; i++) { // 1. 낚시왕이 오른쪽으로 한 칸 이동한다.

			// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
			// 상어를 잡으면 격자판에서 잡은 상어가 사라진다.

			for (int j = 1; j <= R; j++) {
				if (space[j][i] > 0) {
					sum += sharks[space[j][i]].z;
					sharks[space[j][i]].isAlive = false;
					space[j][i] = 0;
					break;
				}
			}

			// 3. 상어가 이동한다.
			for (int j = 1; j <= M; j++) {

				if (!sharks[j].isAlive) // 죽은 상어라면
					continue;

				if (space[sharks[j].r][sharks[j].c] == j) // 내 번호일 때만 지우기
					space[sharks[j].r][sharks[j].c] = 0;
				int s = sharks[j].s; // 움직일 칸 수

				while (s > 0) {
					int move = 0;
					switch (sharks[j].d) {
					case 1:
						move = sharks[j].r - 1;
						if (move > s)
							move = s;
						else
							sharks[j].d = 2;
						sharks[j].r -= move;
						break;
					case 2:
						move = R - sharks[j].r;
						if (move > s)
							move = s;
						else
							sharks[j].d = 1;
						sharks[j].r += move;
						break;
					case 3:
						move = C - sharks[j].c;
						if (move > s)
							move = s;
						else
							sharks[j].d = 4;
						sharks[j].c += move;
						break;
					case 4:
						move = sharks[j].c - 1;
						if (move > s)
							move = s;
						else
							sharks[j].d = 3;
						sharks[j].c -= move;
						break;
					}
					s -= move;
				}

				int check = space[sharks[j].r][sharks[j].c];
				if (check > 0 && check < j) { // 상어가 있는데 이미 이동 완료한 상어라면
					if (sharks[check].z > sharks[j].z) {// 기존에 있던 상어가 더 크다면
						sharks[j].isAlive = false;
					} else {
						sharks[check].isAlive = false;
						space[sharks[j].r][sharks[j].c] = j;
					}

				} else // 상어가 없거나 아직 움직이지 않은 경우
					space[sharks[j].r][sharks[j].c] = j;
			}
		}
		System.out.println(sum);

	} // end of main

} // end of class
