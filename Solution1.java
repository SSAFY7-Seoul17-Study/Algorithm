package SWTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1 {
	
	static int[][] orders = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
	static int N;
	static int[][] info = new int[4][2];
	static boolean[] ck;
	
	static final int loc = 0;
	static final int num = 1;
	static final int max = 1000000;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			for (int i = 1; i <= 3; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				info[i][loc] = Integer.parseInt(st.nextToken());	
				info[i][num] = Integer.parseInt(st.nextToken());	
			}
			
			ck = new boolean[N + 1];
			int min = max;
			for (int i = 0; i < 6; i++) {
				int ret = find(i, 0);
				if (ret < min)
					min = ret;
				Arrays.fill(ck, false);
			}
			sb.append(min).append("\n");
		}
		System.out.print(sb.toString());
	}//end main
	
	static int find(int caseNum, int depth) {
		
		if (depth >= 3)
			return 0;
		
		int gate = orders[caseNum][depth];
		int move = 0;
		
		int start = info[gate][loc];
		int people = info[gate][num];
		
		int s1 = 2;
		int s2 = 2;
		
		int i1 = start - 1;
		if (i1 <= 0) {
			i1 = 1;
			s1 = 1;
		}
		int i2 = start + 1;
		if (i2 > N) {
			i2 = N;
			s2 = 1;
		}
		
		if (!ck[start]) {
			ck[start] = true;
			move++;
			people--;
		}
		while (people > 0) {
			
			while (i1 > 1 && ck[i1]) {
				i1--;
				s1++;
			}
			while (i2 < N && ck[i2]) {
				i2++;
				s2++;
			}
			
			if (i1 <= 1 && i2 >= N && ck[i1] && ck[i2])
				return max;
			
			if (people == 1 && !ck[i1] && !ck[i2] && s1 == s2) {
				//마지막 사람의 선택지가 많은 경우
				ck[i1] = true;
				int ret1 = find(caseNum, depth + 1);
				ck[i1] = false;
				ck[i2] = true;
				int ret2 = find(caseNum, depth + 1);
				if (ret1 > ret2)
					return move + s2 + ret2;
				return move + s1 + ret1;
			}
			
			if (s1 <= s2 || ck[i2]) {
				if (!ck[i1]) {
					ck[i1] = true;
					move += s1;
					people--;
				}
				while (i1 > 1 && ck[i1]) {
					i1--;
					s1++;
				}
			}
			
			if (people <= 0)
				break;
			if (s2 <= s1 || ck[i1]) {
				if (!ck[i2]) {
					ck[i2] = true;
					move += s2;
					people--;
				}
				while (i2 < N && ck[i2]) {
					i2++;
					s2++;
				}
			}
		}
		return move + find(caseNum, depth + 1);
	}
}//end class

/*
 * 게이트 순서에 따라, 각 게이트의 마지막 사람의 선택에 따라 경우가 나뉨
 * 게이트 순서는? 6가지 경우
 * 해당 순서에서 낙시꾼의 배치 경우의 수는?
 * 6가지
 * 
 * 게이트 순서 6가지는? -> 하드코딩하기
 * 낙시꾼 배치 방법은?
 * 낙시터 크기만큼 boolean배열 생성(전역)
 * 각 게이트에서 게이트의 사람 배치했을 때 이동거리 리턴하는 메서드
 * 게이트 순서, depth가 주어져야함
 * 시작점 -> 비어있으면 true
 * 		-> 차있으면 양옆으로
 * 		-> 양옆 중 false인 곳에 true
 * 		-> 만약 마지막 사람이라면?
 * 		-> 다음 게이트의 2가지 경우를 재귀호출
 * 		-> 2가지 경우로 분기하기
 * 
2
10
4 5
6 2
10 2
10
8 5
9 1
10 2
 * */
