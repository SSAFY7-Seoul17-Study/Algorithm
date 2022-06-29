package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BOJ_1715_카드정렬하기_G4_김희영_363ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> arr = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			arr.offer(Integer.parseInt(br.readLine()));
		}

		int ans = 0;

		for (int i = 1; i < N; i++) {
			int sum = arr.poll() + arr.poll();
			ans += sum;
			arr.offer(sum);
		}

		System.out.println(ans);

	} // end of main

} // end of class
