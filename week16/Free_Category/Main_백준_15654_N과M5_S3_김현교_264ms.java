package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_15654_N과M5_S3_김현교_264ms {

	static int N, M;
	static int[] nums, select;
	static boolean[] ck;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(nums);
		
		ck = new boolean[N];
		select = new int[N];
		perm(0);
		System.out.print(sb.toString());
	}//end main

	private static void perm(int level) {
		
		if (level == M) {
			for (int i = 0; i < M; i++) {
				sb.append(select[i]).append(" ");
			}
			sb.append("\n");
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
	
}//end class
/*
 * N개중 M개를 선택하는 순열
 * 
 * */
