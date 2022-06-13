package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_백준_1941_소문난칠공주_G3_김현교_240ms {

	static final int N = 5;
	
	static int cnt;
	static char[][] m = new char[5][5];
	static C[] select = new C[7];
	static int[][] save = new int[5][5];
	
	static Queue<C> q = new LinkedList<>();
	static boolean[] v = new boolean[7];
	
	static int[] dl = {1, 0, -1, 0};
	static int[] dr = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				m[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(save[i], -1);
		}
		
		combi(0, 0, 0, 0);
		System.out.println(cnt);
	}//end main

	private static void combi(int l, int r, int num, int sCnt) {
		
		if (7 - num + sCnt < 4)
			return;
		
		if (num == 7) {
			if (isLink())
				cnt++;
			return;
		}
		
		if (r >= N) {
			r = 0;
			if (++l >= N)
				return;
		}
		
		int i = l;
		int j = r;
		while (i < N) {
			select[num] = new C(i, j);
			save[i][j] = num;
			int sCntAdd = (m[i][j] == 'S') ? 1 : 0;
			combi(i, j + 1, num + 1, sCnt + sCntAdd);
			save[i][j] = -1;
			if (++j >= N) {
				j = 0;
				i++;
			}
		}
	}

	private static boolean isLink() {
		q.offer(select[0]);
		v[0] = true;
		int ckCnt = 1;
		
		while (!q.isEmpty()) {
			C cur = q.poll();
			
			for (int di = 0; di < 4; di++) {
				int nl = cur.l + dl[di];
				int nr = cur.r + dr[di];
				if (nl < 0 || nl >= N || nr < 0 || nr >= N)
					continue;
				
				int num = save[nl][nr];
				if (num >= 0 && !v[num]) {
					q.offer(select[num]);
					v[num] = true;
					ckCnt++;
				}
			}
		}
		
		Arrays.fill(v, false);
		return ckCnt == 7 ? true : false;
	}

	static class C {
		int l, r;

		public C(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}
}//end class
/*
 * [조건]
 * - 가로 세로로 인접한 7명 선택
 * - 선택한 것 중 S가 4개 이상
 * 
 * [완탐 풀이]
 * 시작점에서 가로 세로가 인접하게 7개를 선택 -> 어떻게??
 * 25개중 7개를 선택하는 것
 * 25C7 = 50만
 * 
 * 
 *  -> 조건 확인 후 참이면 경우의수 ++
 * 
 * [가지치기]
 * S를 시작점으로 잡기
 * Y가 4개이상되면 바로 종료 
 * 이전 방문체크로 같은 경우는 skip
 * 
 * */
