package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 회전
 * int 배열로 회전 후 각 원판의 시작 위치를 저장
 * 
 * 시계방향 : start = (start - k + M) % M
 * 반시계방향 : start = (start + k + M) % M
 * 
 * 회전이 끝나면 새로운 int[][] 배열에 start부터 값 저장
 * 
 * 2. 인접체크
 * 상/하/좌/우로 나눠서 인접한 값이 있는지 체크
 * 
 * 만약 인접한 값이 있다면 0으로 값 변경
 * 없다면 평균을 구하고 +1, -1
 * 
 * 3. 총합 출력
 */
public class Main_BOJ_17822_원판돌리기_G3_김희영_120ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][M];
		int[][] brr = new int[T][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				brr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			// 1. 회전
			int x = brr[i][0]; // x의 배수인 원반 돌리기
			int d = brr[i][1]; // 0 : 시계방향, 1 : 반시계방향
			int k = brr[i][2]; // k칸 회전
			int[] pointer = new int[N + 1];

			for (int n = x; n <= N; n += x) { // 회전
				if (d == 0) // 시계
					pointer[n] = (pointer[n] - k + M) % M;
				else // 반시계
					pointer[n] = (pointer[n] + k + M) % M;
			}

			int[][] crr = new int[N + 1][M]; // 새로운 배열에 저장
			for (int r = 1; r <= N; r++) {
				int start = pointer[r];
				for (int j = 0; j < M; j++) {
					crr[r][j] = arr[r][start];
					start += 1;
					if (start == M)
						start = 0;
				}
			}

			boolean[][] v = new boolean[N + 1][M];
			boolean isAdj = false;
			arr = crr;

			 // 2. 인접체크
			for (int r = 1; r <= N; r++) {
				for (int c = 0; c < M; c++) {
					if (arr[r][c] != 0 && !v[r][c]) { // 0이면 이미 지워진 수
						// 상
						if (r != N)
							if (arr[r][c] == arr[r + 1][c]) {
								v[r][c] = true;
								v[r + 1][c] = true;
								isAdj = true;
								continue;
							}

						// 하
						if (r != 1)
							if (arr[r][c] == arr[r - 1][c]) {
								v[r][c] = true;
								v[r - 1][c] = true;
								isAdj = true;
								continue;
							}

						// 좌
						int left = c - 1;
						if (c == 0)
							left = M - 1;
						if (arr[r][c] == arr[r][left]) {
							v[r][c] = true;
							v[r][left] = true;
							isAdj = true;
							continue;
						}

						// 우
						int right = c + 1;
						if (c == M - 1)
							right = 0;
						if (arr[r][c] == arr[r][right]) {
							v[r][c] = true;
							v[r][right] = true;
							isAdj = true;
							continue;
						}
					}
				}
			} // end of for 인접체크

			if (isAdj) { // 인접한 값이 있다면
				for (int r = 1; r <= N; r++) {
					for (int c = 0; c < M; c++) {
						if (v[r][c])
							arr[r][c] = 0;
					}
				}
			} else { // 없다면
				int sum = 0;
				int cnt = 0;
				for (int r = 1; r <= N; r++) {
					for (int c = 0; c < M; c++) {
						if (arr[r][c] != 0) {
							sum += arr[r][c];
							cnt++;
						}
					}
				}

				double avg = (double) sum / (double) cnt;

				for (int r = 1; r <= N; r++) {
					for (int c = 0; c < M; c++) {
						if (arr[r][c] != 0) {
							if (arr[r][c] > avg)
								arr[r][c]--;
							else if (arr[r][c] < avg)
								arr[r][c]++;
						}
					}
				}
			}

		} // end of for T

		// 3. 총합 출력
		int sum = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 0; c < M; c++) {
				sum += arr[r][c];
			}
		}

		System.out.println(sum);

	} // end of main

}
