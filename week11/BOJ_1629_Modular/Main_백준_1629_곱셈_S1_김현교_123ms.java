package Math2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1629_곱셈_S1_김현교_123ms {
	
	static final int ISODD = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long res = 1;
		while (B > 0) {
			if ((B & ISODD) != 0)
				res = (res * A) % C;
			B >>= 1;
			A = (A * A) % C;
		}
		
		System.out.println(res);	
	}//end main
}//end class
/*
 * 분할정복을 통한 거듭제곱
 * 
 * A ^ n
 * 	n짝수 -> n/2 n/2
 * 	n홀수 -> n-1/2 n-1/2 1
 * 
 * */
