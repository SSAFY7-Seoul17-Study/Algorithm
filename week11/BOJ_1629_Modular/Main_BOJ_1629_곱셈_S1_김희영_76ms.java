package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1629_곱셈_S1_김희영_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());

		long m = a % c;
		long ans = pow(m, b, c);
		System.out.println(ans);

	} // end of main

	private static long pow(long a, long b, long c) {
		if (b == 0)
			return 1;
		long ans = pow(a, b / 2, c);
		long next = (ans * ans) % c;
		if (b % 2 == 0)
			return next;
		else
			return (next * a) % c;
	}

} // end of class
