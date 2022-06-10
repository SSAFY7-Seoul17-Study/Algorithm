package study.day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_백준_2407_조합_실3_추준성_80ms {
	/*
	 * nCm = n(n-1)(n-2)...(n-m)/m!
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		BigInteger numerator = BigInteger.ONE;
		BigInteger denominator = BigInteger.ONE;
		for (int i = 0; i < m; i++) {
			numerator = numerator.multiply(new BigInteger(String.valueOf(n-i)));
			denominator = denominator.multiply(new BigInteger(String.valueOf(i+1)));
		}
		
		System.out.print(numerator.divide(denominator));
	} // end of main
	
} // end of class
