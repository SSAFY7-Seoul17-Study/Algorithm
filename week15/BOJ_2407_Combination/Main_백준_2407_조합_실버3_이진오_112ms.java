import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

/**
 * Main_백준_2407_조합_실버3_이진오_112ms
 * 
 *  - 복기
 *    : 정수형 데이터 타입(int, long)의 오버플로우 검출
 *      => x + y > Integer.MAX_VALUE
 *         overflow when x > Integer.MAX_VALUE - y
 *      => x * y > Integer.MAX_VALUE
 *         overflow when x > Integer.MAX_VALUE / y
 *    
 *    : BigDecimal 타입의 활용
 *    
 *      1) 선언
 *         - BigDecimal dec = new BigDecimal(long x);
 *      
 *      2) 연산
 *         - dec = dec.add(new BigDecimal(long y));
 *           => Immutable 객체이므로, 연산 이후에도 값이 변하지 않는다. 
 * 
 */
public class Main_백준_2407_조합_실버3_이진오_112ms {
	
	private static final long MAX = Long.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		m = (n - m) > m ? m : n - m;
		
		long ans = 1L;
		
		int denom = n;
		int numer = m;
		
		while (denom > n - m) {
			if (ans <= MAX / denom) {
				ans *= denom--;
			}
			else
				break;
		}
		
		if (denom == n - m) {
			while (numer > 0)
				ans /= numer--;
			
			System.out.println(ans);
		}
		else {
			BigDecimal ansB = new BigDecimal(ans);
			
			while (denom > n - m) {
				ansB = ansB.multiply(new BigDecimal(denom--), MathContext.UNLIMITED);
			}
			
			
			while (numer > 0)
				ansB = ansB.divide(new BigDecimal(numer--), BigDecimal.ROUND_CEILING);
			
			System.out.println(ansB);
		}
		
		sc.close();
	}
}
