package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10819_차이를최대로_S2_김현교_128ms {

	static int N, max;
	static int[] nums, select;
	static boolean[] ck;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		ck = new boolean[N];
		select = new int[N];
		perm(0);
		System.out.println(max);
	}//end main

	private static void perm(int level) {
		
		if (level == N) {
			int sum = getSum();
			if (max < sum) {
				max = sum;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (ck[i])
				continue;
			ck[i] = true;
			select[level] = nums[i];
			perm(level + 1);
			ck[i] = false;
		}
	}
	
	static int getSum() {
		int sum = 0;
		for (int i = 0; i < N - 1; i++) {
			sum += Math.abs(select[i] - select[i + 1]);
		}
		return sum;
	}
}//end class
/*
 * 
 * 순서에 맞게 N개중 N개를 선택 -> 순열
 * 
 * */
