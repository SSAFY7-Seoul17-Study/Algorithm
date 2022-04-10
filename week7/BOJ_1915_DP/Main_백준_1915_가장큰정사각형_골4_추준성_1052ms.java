package study.day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1915_가장큰정사각형_골4_추준성_1052ms {
	/*
	 * <설계>
	 * 1. 탐색하다가 1을 만나면 정사각형 체크
	 * 2. 가장 큰 정사각형의 한 변의 길이를 저장/갱신해두고 이를 기준으로 재탐색 
	 * 3. 1, 2를 반복
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int len = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '1') {
label:				while(true) {
						if(i+len >= N || j+len >= M || map[i+len][j] != '1' || map[i][j+len] != '1') break label;
						for (int r = i; r <= i+len; r++) {
							for (int c = j; c <= j+len; c++) {
								if(map[r][c] != '1') break label;
							}
						}
						len++;
					}
				}
			}
		}
		
		System.out.println(len * len);
		
	} // end of main

} // end of class
