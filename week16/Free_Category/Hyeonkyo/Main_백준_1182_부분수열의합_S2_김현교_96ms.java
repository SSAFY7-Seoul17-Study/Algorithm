package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1182_부분수열의합_S2_김현교_96ms {

	static int N, S, cnt;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		subset(0, 0, false);
		System.out.println(cnt);
	}//end main

	private static void subset(int level, int sum, boolean flag) {
		
		if (level == N) {
			if (flag && sum == S)
				cnt++;
			return;
		}
		
		subset(level + 1, sum, flag);
		subset(level + 1, sum + nums[level], true);
	}
	
}//end class
/*
 * N개의 숫자 중 1개이상을 선택하는 경우에 합이 S인 경우를 추가한다.
 * 
 * 부분집합에 합이 S인 경우 추가
 * 
 * */
