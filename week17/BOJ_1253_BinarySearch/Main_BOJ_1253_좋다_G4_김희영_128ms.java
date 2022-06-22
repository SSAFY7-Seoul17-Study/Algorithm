package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1번 1개의 원소를 기준으로 합하여 해당 값이 되는 2개의 원소 찾기
 * 2번 2개의 원소를 기준으로 2개를 합한 값과 같은 1개의 원소 찾기
 * 
 * => 1번
 * 1. 정렬
 * 2. for 문을 돌며 합하여 합하여 해당 원소 값이 되는 2개의 원소가 있는지 투포인터로 탐색
 */
public class Main_BOJ_1253_좋다_G4_김희영_128ms {

	private static int[] arr;
	private static int right;
	private static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		right = N - 1;
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 정렬

		ans = 0;

		for (int i = 0; i < N; i++) {
			tp(i);
		}

		System.out.println(ans);

	} // end of main

	private static void tp(int idx) {
		int l = 0;
		int r = right;
		int n = arr[idx];

		while (true) {
			if (l == idx) // 자기 자신은 제외
				l++;
			if (r == idx) // 자기 자신은 제외
				r--;
			if (l >= r) // 종료 조건
				return;

			int sum = arr[l] + arr[r];
			if (sum == n) {
				ans++;
				return;
			} else if (sum < n) // 작으면 왼쪽을 움직여 합을 키우기
				l++;
			else // 크면 오른쪽을 움직여 합을 줄이기
				r--;
		}
	}

} // end of class