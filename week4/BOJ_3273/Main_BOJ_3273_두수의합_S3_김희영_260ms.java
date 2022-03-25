package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_3273_두수의합_S3_김희영_260ms {

	private static int[] arr;

	/**
	 * 이분탐색을 이용해서 합이 x인 숫자 쌍 찾기
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());

		Arrays.sort(arr);

		int cnt = 0;

		int start = 0;
		int last = n - 1;
		int num = x - arr[start]; // 우리가 찾는 수

		// 우리가 찾는 수가 이번 수보다 작으면 이 뒤에 없는 것(중복제거)
		while (num > arr[start] && start < last) {
			int result = binarySearch(start, last, num);

			if (result > 0) {
				last = result;
				cnt++;
			}

			start++;

			num = x - arr[start];
		}

		System.out.println(cnt);
	} // end of main

	private static int binarySearch(int start, int last, int num) {

		if (start > last) // 끝까지 못 찾은 경우
			return -1;

		int mid = (start + last) >> 1;

		if (arr[mid] == num) // 찾은 경우
			return mid;
		else if (arr[mid] < num)
			start = mid + 1;
		else
			last = mid - 1;

		return binarySearch(start, last, num);
	}

}
