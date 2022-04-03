package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_12851_숨바꼭질2_G5_김희영_배열_172ms {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 수빈이 초기 위치
		int K = sc.nextInt(); // 동생 위치
		boolean[] v = new boolean[100001];

		int[][] arr = new int[1000000][2]; // 큐 대신 쓰는 배열
		// 90만까지는 ArrayIndexOutOfBoundsException

		int sec = -1; // 동생을 찾는 가장 빠른 시간
		int cnt = 0; // 방법의 수

		arr[0][0] = N;
		arr[0][1] = 0;

		int point = 0; // 큐의 맨 첫 번째 원소(poll)
		int end = 1; // 배열의 마지막 번호(다음 원소가 들어올 자리)

		boolean notYet = true; // 동생을 찾기 전인지

		while (notYet) {
			sec++;
			int len = end - point; // 한 너비에 몇 개의 원소가 있는지
			for (int i = 0; i < len; i++) {
				int x = arr[point][0];
				int flag = arr[point++][1];
				v[x] = true;

				if (x == K) {
					cnt++;
					notYet = false;
				}
				if (notYet) {
					if (flag != 1 && x > 0) {
						int tmp = x - 1;
						if (!v[tmp]) {
							arr[end][0] = tmp;
							arr[end++][1] = -1;
						}
					}

					if (K > x) {

						if (flag != -1 && x < 100000) {
							int tmp = x + 1;
							if (!v[tmp]) {
								arr[end][0] = tmp;
								arr[end++][1] = 1;
							}
						}
						if (x <= 50000) {
							int tmp = x << 1;
							if (!v[tmp]) {
								arr[end][0] = tmp;
								arr[end++][1] = 0;
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
