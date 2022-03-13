package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [초기세팅] N구 멀티탭이 모두 찰 때까지 전자 제품을 꽂음
 *	1. 다음에 사용할 전기 제품이 멀티탭에 꽂혀 있는 경우 -> 패스
 *	2. 다음에 사용할 전기 제품이 멀티탭에 꽂혀 있지 않은 경우
 *  	2.1. 사용 순서를 순회하여 현재 멀티탭에 꽂힌 제품 중 앞으로 사용될 일이 없는 콘센트를 선택
 * 		2.2. 2.1이 존재하지 않는다면 가장 늦게 사용되는 콘센트를 선택
 */
public class Main_BOJ_1700_멀티탭스케줄링_G1_김희영_발표용_80ms {

	private static int N, K, ans;
	private static int[] arr;
	private static boolean[] used;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 수
		K = Integer.parseInt(st.nextToken()); // 전기용품 사용횟수
		arr = new int[K]; // 전기 용품 사용 순서
		used = new boolean[K + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int idx = 0, cnt = 0;
		while (true) {		// 초기세팅
			if (cnt == N)
				break;
			if (!used[arr[idx]]) {
				used[arr[idx]] = true;
				cnt++;
			}
			idx++;
		}

		while (idx < K) {
			// 2. 다음에 사용할 전기 제품이 멀티탭에 꽂혀 있지 않은 경우
			if (!used[arr[idx]]) {
				List<Integer> list = new ArrayList<>();	// 멀티탭에 꽂힌 전자 제품 중 나중에 다시 사용되는 제품을 저장하는 list
				for (int i = idx; i < K; i++) {
					if (used[arr[i]] && !list.contains(arr[i])) {	// 멀티탭에 꽂혀 있고 list에 넣지 않은 경우 list에 추가
						list.add(arr[i]);
					}
				}

				// list 사이즈가 N이라는 얘기는 지금 멀티탭에 꽃혀있는 콘센트 모두가 다음에 또
				// 다시 사용된다는 의미이다. 이때는 그나마 가장 늦게 다시 사용될 콘센트를 뽑는다.
				if (list.size() == N) {
					int remove = list.get(list.size() - 1);
					used[remove] = false;
				} else {
					for (int j = 1; j <= K; j++) {
						if (used[j] && !list.contains(j)) {
							used[j] = false;
							break;
						}
					}
				}

				used[arr[idx]] = true;
				ans++;
			}

			idx++;
		}

		System.out.println(ans);

	} // end of main

}
