package Binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1072_게임_S4_김현교_124ms {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		int Z = (int)((double)Y / X * 100);
		if (Z >= 99) {
			System.out.println(-1);
			return;
		}
		
		int target = Z + 1;
//		System.out.println((t * X - 100 * Y) / (100 - t) + 1);
//		System.out.printf("%.2f\n", (double)(t * X - 100 * Y) / (100 - t));
//		System.out.printf("%.0f\n", (double)(t * X - 100 * Y) / (100 - t));
//		System.out.println((int)Math.ceil((double)(t * X - 100 * Y) / (100 - t)));
		System.out.println(binarySearch(X, Y, target));
	}//end main

	private static int binarySearch(int x, int y, int target) {
		
		long min = 0;
		long max = x + 1;
		
		while (min < max) {
			
			long mid = (max + min) / 2;
			
			if ((y + mid) * 100 / (x + mid) < target)
				min = mid + 1;
			else
				max = mid;
		}
		
		return (int)min;
	}

}
/*
 * 구해야 하는 값 -> 횟수 n
 * 
 * Z가 99%, 100%인 경우는 절대 바뀔 수 없음
 * 
 * 나머지의 경우는 확률 계산 시 Z + 1이 되어야 함.
 * y + n / x + n * 100 = z + 1
 * 
 * 0 ~ X사이의 횟수를 이분탐색
 * 
 * */
