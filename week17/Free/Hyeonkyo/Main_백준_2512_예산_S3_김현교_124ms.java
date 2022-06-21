package Binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2512_예산_S3_김현교_124ms {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] budgets = new int[N];
		
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			budgets[i] = Integer.parseInt(st.nextToken());
			sum += budgets[i];
		}
		Arrays.sort(budgets);
		int M = Integer.parseInt(br.readLine());
		if (sum <= M) {
			System.out.println(budgets[N - 1]);
			return;
		}
		
		System.out.println(upperBinarySearch(budgets, M));
	}

	private static int upperBinarySearch(int[] budgets, int M) {
		long min = 0;
		long max = budgets[N - 1] + 1;
		
		while (min < max) {
			long mid = (max + min) / 2;
			
			int sum = 0;
			for (int budget : budgets) {
				sum += budget >= mid ? mid : budget;
			}
			
			if (sum <= M) min = mid + 1;
			else max = mid;
		}
		
		return (int)(min - 1);
	}
}
/*
 * 초기값으로 해결이 되는 경우 바로 그 최대값 리턴
 * 
 * */
