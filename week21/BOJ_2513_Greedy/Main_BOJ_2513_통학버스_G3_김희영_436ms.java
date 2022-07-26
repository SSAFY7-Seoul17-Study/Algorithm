package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2513_통학버스_G3_김희영_436ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int sumL = 0, sum = 0;
		int[][] apart = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			apart[i][0] = Integer.parseInt(st.nextToken()); // 좌표
			apart[i][1] = Integer.parseInt(st.nextToken()); // 학생수

			if (apart[i][0] < S)
				sumL++;
		}

		Arrays.sort(apart, (a, b) -> a[0] - b[0]); // 좌표로 정렬

		int index = 0; // 아파트 인덱스

		// 왼쪽
		while (index < sumL) {
			int k = 0, m = (S - apart[index][0]) << 1; // k : 승객 수, m : 이동 거리

			// 승객 태우기
			while (k < K && index < sumL) {
				if (apart[index][1] <= K - k) {
					k += apart[index][1];
					apart[index++][1] = 0;
				} else {
					apart[index][1] -= K - k;
					k = K;
				}
			}

			// 내리기
			sum += m;
			k = 0;
		}

		int p = index;
		index = N - 1;
		// 오른쪽
		while (index >= p) {
			int k = 0, m = (apart[index][0] - S) << 1; // k : 승객 수, m : 이동 거리

			// 승객 태우기
			while (k < K && index >= p) {
				if (apart[index][1] <= K - k) {
					k += apart[index][1];
					apart[index--][1] = 0;
				} else {
					apart[index][1] -= K - k;
					k = K;
				}
			}

			// 내리기
			sum += m;
			k = 0;
		}

		System.out.println(sum);
	} // end of main

} // end of class
