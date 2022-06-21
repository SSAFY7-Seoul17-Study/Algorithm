package Binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_10816_숫자카드2_S4_김현교_1044ms {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int key = Integer.parseInt(st.nextToken());
			int cnt = upperBinarySearch(cards, key) - lowerBinarySearch(cards, key);
			sb.append(cnt).append(" ");
		}
		
		System.out.println(sb.toString());
	}

	private static int upperBinarySearch(int[] cards, int key) {
		int min = 0;
		int max = N;
		
		while (min < max) {
			int mid = (max + min) / 2;
			
			if (cards[mid] <= key) min = mid + 1;
			else max = mid;
		}
		
		return min;
	}
	
	private static int lowerBinarySearch(int[] cards, int key) {
		int min = 0;
		int max = N;
		
		while (min < max) {
			int mid = (max + min) / 2;
			
			if (cards[mid] < key) min = mid + 1;
			else max = mid;
		}
		
		return min;
	}
}
