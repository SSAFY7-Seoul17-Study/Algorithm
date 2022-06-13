package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_17144_미세먼지안녕_G4_김현교_272ms {
	
	static int R, C, T, total;
	static int[][][] m;
	static int[] airCleaner = new int[2];
	
	static int[] dl = {-1, 0, 1, 0};//상우하좌
	static int[] dr = {0, 1, 0, -1};
	
	static final int CUR = 0;
	static final int NEXT = 1;
	static final int TOP = 0;
	static final int BOT = 1;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		m = new int[2][R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				m[CUR][i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < R; i++) {
			if (m[CUR][i][0] == -1) {
				airCleaner[TOP] = i;
				airCleaner[BOT] = i + 1;
				m[NEXT][i][0] = -1;
				m[NEXT][i + 1][0] = -1;
				break;
			}
		}
		
		while (T-- > 0) {
			spread();
			circulate();
		}
		
		System.out.println(getTotal());
	}//end main
	
	static void spread() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int dust = m[CUR][i][j];
				if (dust <= 0)
					continue;
				
				int spreadCnt = 0;
				for (int di = 0; di < 4; di++) {
					int nl = i + dl[di];
					int nr = j + dr[di];
					if (nl < 0 || nl >= R || nr < 0 || nr >= C
						|| m[NEXT][nl][nr] < 0)
						continue;
					spreadCnt++;
					m[NEXT][nl][nr] += dust / 5;
				}
				m[NEXT][i][j] += dust - (dust / 5) * spreadCnt;
			}
		}
		int[][] tmp = m[NEXT];
		m[NEXT] = m[CUR];
		m[CUR] = tmp;
		for (int i = 0; i < R; i++) {
			Arrays.fill(m[NEXT][i], 0);
		}
		m[NEXT][airCleaner[TOP]][0] = -1;
		m[NEXT][airCleaner[BOT]][0] = -1;
	}
	
	private static void circulate() {
		for (int loc = 0; loc < 2; loc++) {
			int l = loc == TOP ?
					airCleaner[TOP] - 1 : airCleaner[BOT] + 1;
			int r = 0;
			int di = loc == TOP ? 0 : 2;
			int diff = loc == TOP ? 1 : -1;
			while (true) {
				int nl = l + dl[di];
				int nr = r + dr[di];
				if (notVaildIndex(nl, nr, loc)) {
					di += diff;
					if (di < 0) di = 3;
					continue;
				}
				m[CUR][l][r] = m[CUR][nl][nr];
				if (m[CUR][l][r] == -1) {
					m[CUR][l][r] = 0;
					break;
				}
				l = nl;
				r = nr;
			}
		}
	}
	
	private static boolean notVaildIndex(int nl, int nr, int loc) {
		if (nr < 0 || nr >= C)
			return true;
		if (loc == TOP)
			return nl < 0 || nl > airCleaner[loc];
		return nl >= R || nl < airCleaner[loc]; 
	}

	private static int getTotal() {
		int total = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (m[CUR][i][j] > 0) {
					total += m[CUR][i][j];
				}
			}
		}
		return total;
	}
}//end class

/*
 * 1. 확산
 * 	- 모든 미세먼지가 동일한 타이밍에 같은 조건으로 확산
 * 	- origin미세먼지, 확산을 계산할 미세먼지 배열 2개로 나눠서 계산
 * 
 * 2. 공기청정기의 순환
 * 	- 위아래로 두칸 이상 떨어져 있음 -> 항상 사각형 모양의 순환 가능
 *  - top, bottom별로 순환 로직 짜면 될듯
 * 
 * */
