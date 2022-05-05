import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_1629_곱셈_실버1_이진오_76ms
 * 
 *  - mod 연산
 *    : x의 y승 (mod d)
 *  
 *  - 오답
 *    : x의 1승에 대한 (mod d)를 취해주지 않은 경우
 * 
 */
public class Main_백준_1629_곱셈_실버1_이진오_76ms {
	public static void main(String[] args) throws IOException {	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		// x^y (mod d)
		
		System.out.println(pow(x, y, d));
	}
	
	public static long pow(long x, long y, long d) {
		if (y == 1)
			return x % d;
		
		long temp = pow(x, y/2, d);
		temp = (temp * temp) % d;
		if (y % 2 == 0) {
			return temp;
		}
		else {
			return (temp * x) % d;
		}
	}
}
