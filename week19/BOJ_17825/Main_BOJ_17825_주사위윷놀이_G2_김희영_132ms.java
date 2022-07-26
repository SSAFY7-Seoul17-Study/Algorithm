package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재귀 함수를 이용하여 각 턴에 말이 움직이는 모든 경우를 계산
 */
public class Main_BOJ_17825_주사위윷놀이_G2_김희영_132ms {

	// 점수, 파란 화살표(없다면 빨간 화살표), 빨간 화살표
	private static int[][] map = { { 0, 1, 1 }, { 2, 2, 2 }, { 4, 3, 3 }, { 6, 4, 4 }, { 8, 5, 5 }, { 10, 6, 9 },
			{ 13, 7, 7 }, { 16, 8, 8 }, { 19, 28, 28 }, { 12, 10, 10 }, { 14, 11, 11 }, { 16, 12, 12 }, { 18, 13, 13 },
			{ 20, 14, 16 }, { 22, 15, 15 }, { 24, 28, 28 }, { 22, 17, 17 }, { 24, 18, 18 }, { 26, 19, 19 },
			{ 28, 20, 20 }, { 30, 21, 24 }, { 28, 22, 22 }, { 27, 23, 23 }, { 26, 28, 28 }, { 32, 25, 25 },
			{ 34, 26, 26 }, { 36, 27, 27 }, { 38, 31, 31 }, { 25, 29, 29 }, { 30, 30, 30 }, { 35, 31, 31 },
			{ 40, 32, 32 }, { 0, 32, 32 } };
	private static int[] marker = { 0, 0, 0, 0 };
	private static int[] dice;
	private static int max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		dice = new int[10];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		move(0, 0);

		System.out.println(max);

	} // end of main

	private static void move(int cnt, int sum) {
		if (cnt == 10) {
			if (sum > max)
				max = sum;
			return;
		}

		here: for (int i = 0; i < 4; i++) {

			int moveCnt = dice[cnt] - 1;
			int togo = map[marker[i]][1];
			while (moveCnt-- > 0 && togo != 32) {
				togo = map[togo][2];
			}

			for (int j = 0; j < 4; j++) {
				if (i == j)
					continue;
				if (togo != 32 && marker[j] == togo)
					continue here;
			}

			int org = marker[i];
			marker[i] = togo;
			move(cnt + 1, sum + map[togo][0]);
			marker[i] = org;

		}
	}

} // end of class
