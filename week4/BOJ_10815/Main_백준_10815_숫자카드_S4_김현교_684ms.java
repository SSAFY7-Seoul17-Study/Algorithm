package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10815_숫자카드_S4_김현교_684ms {
	
	public static void main(String[] args) throws Exception {
		
		int offset = 10000000;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] ck = new boolean[20000001];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			ck[Integer.parseInt(st.nextToken()) + offset] = true;
		}
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			if (ck[Integer.parseInt(st.nextToken()) + offset])
				sb.append("1 ");
			else
				sb.append("0 ");
		}
		sb.append("\n");
		System.out.print(sb.toString());
	}
}//end class
