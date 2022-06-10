package study.day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_2448_별찍기11_골4_추준성_492ms {
	/*
	 * - N = 3 * 2^k => 2의 거듭제곱만큼 삼각형 층수가 결정됨
	 * - 규칙성 찾기 => 단위 삼각형을 그리기 시작할 맨 위 꼭짓점 위치를 찾기
	 */
	private static char[][] map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new char[N][2*N-1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2*N-1; j++) {
				map[i][j] = ' '; // 공백으로 초기화
			}
		}
		
		star(0, N-1, N); // 맨 윗 꼭짓점부터 재귀 시작 => 각 삼각형의 맨 위 꼭짓점 좌표를 재귀적으로 찾기
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2*N-1; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		
	} // end of main
	
	static void star(int r, int c, int n) {
		if(n == 3) { // 기저조건
			map[r][c] = '*';
			map[r+1][c-1] = map[r+1][c+1] = '*';
			for (int i = 0; i < 5; i++) {
				map[r+2][c-2+i] = '*'; 
			}
			return;
		}
		
		star(r, c, n/2); // 위 삼각형
		star(r+n/2, c-n/2, n/2); // 아래 왼쪽 삼각형
		star(r+n/2, c+n/2, n/2); // 아래 오른쪽 삼각형
	}
	
} // end of class
