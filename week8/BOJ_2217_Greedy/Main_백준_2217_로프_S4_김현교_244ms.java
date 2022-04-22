package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_2217_로프_S4_김현교_244ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] weight = new int[N];
		for (int i = 0; i < N; i++)
			weight[i] = Integer.parseInt(br.readLine());
		Arrays.sort(weight);

		long max = 0;
		for (int i = N - 1; i >= 0; i--) {
			long curWeight = weight[i] * (long)(N - i);
			if (curWeight > max)
				max = curWeight;
		}
		System.out.println(max);
	}//end main
}
