package Binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * - 길이가 각기다른 K개의 랜선을 잘라서 길이가 같은 N개의 랜선을 만들기
 * - 각각의 랜선이 N으로 나눈 몫만큼 랜선이 나옴
 * - N의 최대 길이 값을 구하라.
 * 
 * ------------
 * 
 * 기존 이분 탐색 -> 정렬된 값들에서 인덱스를 반씩 나누며 이분탐색 진행
 * 
 * 이 문제는 인덱스가 아닌 하나의 최대 랜선길이 x를 찾는 것임
 * 랜선의 최소길이 0, 최대길이 = 길이 max값을 잡고
 * 중간값 mid가 N이 될때까지 이분탐색을 진행 (Upper Bound)
 * 그 값에 -1을 한 값이 최댓값이다.
 * 
 * [주의]
 * 1. upper를 구할 때 mid가 0이 되지 않도록, max초기값에 max + 1을 넣어줌
 * 2. 오버플로우 주의
 * */

public class Main_백준_1654_랜선자르기_S2_김현교_148ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] wire = new int[K];
		for (int i = 0; i < K; i++) {
			wire[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(wire);
		
		System.out.println(binarySearch(wire, N));
		
	}//end main

	private static int binarySearch(int[] wire, int n) {
		
		long min = 0;
		long max = (long)wire[wire.length - 1] + 1;
		long mid = 0;
		
		while (min < max) {
			
			mid = (min + max) / 2;
			int cnt = 0;
			for (int len : wire) {
				cnt += len / mid;
			}
			
			if (cnt < n) max = mid; 
			else min = mid + 1;
		}
		return (int)(min - 1);
	}
	
}//end class

