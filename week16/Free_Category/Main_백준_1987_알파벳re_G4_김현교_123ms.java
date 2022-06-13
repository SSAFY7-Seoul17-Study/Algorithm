package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1987_알파벳re_G4_김현교_123ms {

	static int R, C, max;
	static char[][] m;
	static boolean[] ck;
	
	static int[] dl = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		m = new char[R][];
		for (int i = 0; i < R; i++)
			m[i] = br.readLine().toCharArray();
		
		ck = new boolean['Z' + 1];
		ck[m[0][0]] = true;
		find(0, 0, 1);
		
		System.out.println(max);
	}//end main
	
	static void find(int l, int r, int cnt) {
		if (cnt > max)
			max = cnt;
		
		for (int i = 0; i < 4; i++) {
			int nl = l + dl[i];
			int nr = r + dr[i];
			if (nl < 0 || nl >= R || nr < 0 || nr >= C || ck[m[nl][nr]]) {
				continue;
			}
			
			ck[m[nl][nr]] = true;
			find(nl, nr, cnt + 1);
			ck[m[nl][nr]] = false;
		}
	}
}//end class
