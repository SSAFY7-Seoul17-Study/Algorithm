/**
 * 최장 공통 부분 수열
 * 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제
 * 
 * 1. 완전탐색
 * s1의 부분수열을 모두 구한다.
 * s2의 부분수열을 모두 구한다.
 * s1의 부분수열과 s2의 부분수열을 비교한다
 * 1-1. 길이 1부터 비교 시
 * 더 긴 공통의 부분수열이 등장하면 갱신한다. 하나의 공통 부분 수열이 찾아지면 해당 길이에서는 탐색을 종료한다.
 * 부분 수열의 길이가 s1이나 s2중 짧은 것의 길이와 같아지면 탐색을 종료한다.
 * 
 * 1-2. s1 또는 s2의 길이 중 짧은 것 부터 시작
 * 공통의 부분수열이 등장하는 순간 탐색을 종료한다.
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_백준_9252_LCS2_G4_김인태__fail {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		
		
//		System.out.println("s1: "+Arrays.toString(s1) + ", " + s1[s1.length-1]+", "+(s1.length-1));
//		System.out.println("s2: "+Arrays.toString(s2)+ ", " + s2[s2.length-1]+", "+(s2.length-1));
		
		int[][] lcs = null;
		lcs = new int[s1.length+1][s2.length+1];

		// lcs dp
		for(int i=1; i<s1.length+1; i++) {
			for(int j=1; j<s2.length+1; j++) {
				if(s1[i-1]==s2[j-1])
					lcs[i][j] = 1+lcs[i-1][j-1];
				else
					lcs[i][j] = Integer.max(lcs[i-1][j], lcs[i][j-1]);
			}
		}
		
		
//		for(int i=0;i<s1.length+1;i++) {
//			System.out.println(Arrays.toString(lcs[i]));
//		}
		// lcs 출력
//		StringBuilder sb = new StringBuilder();
//		int i = s1.length;	// s1
//		int j = s2.length;	// s2
//		
//		while(i!=0 && j!=0) {
//			System.out.println("========================================");
//			System.out.println("i="+(i-1)+": "+"s1["+(i-1)+"]="+s1[i-1]+", "+"j="+(j-1)+": "+"s2["+(j-1)+"]="+s2[j-1]);
//			if(s1[i-1]==s2[j-1]) {
//				System.out.println("i="+(i-1)+": "+"s1["+(i-1)+"]="+s1[i-1]+", "+"j="+(j-1)+": "+"s2["+(j-1)+"]="+s2[j-1]);
//			}
//					
//			if(lcs[i-1][j]==lcs[i][j-1]) {
//				sb.append(s2[j-1]);				
//				i--; j--; continue;
//			}
//			if(lcs[i-1][j]<lcs[i][j-1]) {
//				j--; continue;
//			}
//			if(lcs[i-1][j]>lcs[i][j-1]) {
//				i--; continue;
//			}
//		}
//		sb.append("\n").append(lcs[s1.length][s2.length]);
//		sb.reverse();
//		System.out.println(sb);
		
//		108ms
		int len = lcs[s1.length][s2.length];
		char[] result = new char[len];
		
		int idx = len - 1;
		int i = s1.length;
		int j = s2.length;
		while (true) {
			int cur = lcs[i][j];
			if (cur == 0) break;
			
			if (lcs[i][j - 1] == cur)
				j--;
			else if (lcs[i - 1][j] == cur)
				i--;
			else {
				result[idx--] = s1[--i];
				j--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(len).append("\n");
		for (int k = 0; k < len; k++) {
			sb.append(result[k]);
		}
		sb.append("\n");
		System.out.print(sb.toString());
		
	}	// end of main

}