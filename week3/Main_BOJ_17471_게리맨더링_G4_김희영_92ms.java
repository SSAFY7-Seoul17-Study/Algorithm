package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. nC1 ~ nCn/2 까지 부분집합을 찾기
 * 2. 연결 가능 여부 체크
 * 3. 나눠진 선거구의 인구 차이가 최솟값인지 체크
 */
public class Main_BOJ_17471_게리맨더링_G4_김희영_92ms {

	private static int N;
	private static int min = Integer.MAX_VALUE;
	private static int[] population;
	private static boolean[] com;
	private static int[][] list;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		population = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		int cntZero = 0;
		int idxZero = 0;

		list = new int[N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int size = Integer.parseInt(st.nextToken());
			list[i] = new int[size];
			if (size == 0) {
				cntZero++;
				if (cntZero == 2 && N != 2) {
					System.out.println(-1);
					System.exit(0);
				}
				idxZero = i;
			} else {
				for (int j = 0; j < size; j++) {
					list[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}

		}

		if (cntZero == 1) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum += population[i];
			}

			sum -= population[idxZero];
			sum -= population[idxZero];
			sum = Math.abs(sum);
			System.out.println(sum);
			System.exit(0);
		}

		for (int i = 1; i <= N / 2; i++) {
			com = new boolean[N];
			combination(i, 0, 0);
		}

		System.out.println(min);

	} // end of main

	private static void combination(int n, int cnt, int start) {
		if (cnt == n) {
			simulation(n);
			return;
		}

		if (start == N)
			return;

		com[start] = true;
		combination(n, cnt + 1, start + 1);
		com[start] = false;
		combination(n, cnt, start + 1);

	} // end of method combination

	private static void simulation(int n) {
		int ft = 0, ff = 0;
		for (int i = 0; i < N; i++) {
			if (com[i] == true) {
				ft = i;
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			if (com[i] == false) {
				ff = i;
				break;
			}
		}

		boolean[] v = new boolean[N];
		dfs(v, true, ft);
		dfs(v, false, ff);

		boolean can = true;
		for (int i = 0; i < N; i++) {
			if (v[i] == false) {
				can = false;
				break;
			}
		}

		if (can) {
			int sumA = 0, sumB = 0;
			for (int i = 0; i < N; i++) {
				if (com[i])
					sumA += population[i];
				else
					sumB += population[i];
			}
			int sum = Math.abs(sumA - sumB);
			if (sum < min)
				min = sum;
		}

	} // end of method simulation

	private static void dfs(boolean[] v, boolean flag, int crt) {
		v[crt] = true;
		for (int i = 0; i < list[crt].length; i++) {
			if (!v[list[crt][i]] && com[list[crt][i]] == flag)
				dfs(v, flag, list[crt][i]);
		}

	}

}
