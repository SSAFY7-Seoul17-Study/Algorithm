package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 입력받은 직후엔 N구 멀티탭이 모두 찰 때까지 전자 제품을 꽂음
 * 2. 만약 이때 K개의 전자제품을 모두 꽂으면 플러그를 뺄 필요가 없으므로 0을 출력 후 종료
 * 3. 그 이후에는 멀티탭이 꽂혀있는 전자 제품 중 앞으로 사용하지 않거나 가장 나중에 사용하는 전자 제품의 플러그를 빼고 사용
 */
public class Main_BOJ_1700_멀티탭스케줄링_G1_김희영_76ms {

	private static int min = Integer.MAX_VALUE;
	private static int N;
	private static int K;
	private static int[] arr;
	private static int[] brr;
	private static boolean[] v;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[K]; // 전기 용품 사용 순서
		brr = new int[N]; // 멀티탭
		v = new boolean[K + 1]; // 현재 그 숫자가 멀티탭에 꽂혀 있는지

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int p = 0; // 사용 순서 arr을 순회하는 포인터
		int q = 0; // 멀티탭 brr을 순회하는 포인터

		while (p < K && q < N) { // N구 멀티탭이 모두 찰 때까지 전자 제품을 꽂음
			while (p < K && v[arr[p]]) {
				p++;
			}

			if (p >= K) { // K개의 전자제품을 모두 꽂으면 플러그를 뺄 필요가 없으므로 0을 출력 후 종료
				System.out.println(0);
				System.exit(0);
			}

			brr[q] = arr[p++];
			v[brr[q++]] = true;
		}

		greedy(p, 0);

		System.out.println(min);

	} // end of main

	private static void greedy(int p, int cnt) {

		while (p < K && v[arr[p]]) // arr[p]가 멀티탭 안에 있다면
			p++; // 다음 p를 검사

		if (p == K) { // p가 arr의 끝까지 갔다면 종료
			if (cnt < min)
				min = cnt;
			return;
		}

		int[] count = new int[N]; // 얼마나 멀리 있는지
		int max = 0; // 제일 먼 값
		int idx = 0; // 제일 먼 인덱스

		for (int i = 0; i < N; i++) {

			for (int j = p; j < K; j++) { // arr의 현재 위치부터 마지막까지 순회하며 brr[i]값을 찾음
				if (brr[i] == arr[j]) {
					count[i] = j;
					break;
				}
			}

			if (count[i] == 0) { // 0이면 arr에 멀티탭 brr[i] 값이 없는 것
				idx = i;
				break;
			}

			if (count[i] > max) { // 제일 멀리 있는 brr[i] 값을 결정
				max = count[i];
				idx = i;
			}
		}

		v[brr[idx]] = false;
		brr[idx] = arr[p];
		v[brr[idx]] = true;

		greedy(p + 1, cnt + 1);

	}

}
