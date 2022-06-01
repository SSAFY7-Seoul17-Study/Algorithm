import java.util.Arrays;
import java.util.Scanner;

/**
 * Main_백준_2448_별찍기11_골드4_이진오_384ms
 * 
 *  - 재귀 함수로 해결
 * 
 */
public class Main_백준_2448_별찍기11_골드4_이진오_384ms {
	
	static final char SPACE = ' ';
	static final char STAR = '*';
	
	static char[][] array;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		array = new char[N][2*N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(array[i], SPACE);
		}
		
		f(N);
		
		for (int i = 0; i < N; i++)
			System.out.println(new String(array[i]));
		
		sc.close();
	}
	
	public static void f(int n) {
		// 기저 조건
		if (n == 3) {
			array[0][2] = STAR;
			array[1][1] = STAR;
			array[1][3] = STAR;
			for (int i = 0; i < 5; i++)
				array[2][i] = STAR;
			
			return;
		}
		
		// 내려간다 - 분할
		f(n/2);
		
		// 정복
		for (int i = n/2; i < n; i++) {
			System.arraycopy(array[i-n/2], 0, array[i], 0, n);
			System.arraycopy(array[i-n/2], 0, array[i], n, n);
		}
		for (int i = 0; i < n/2; i++) {
			System.arraycopy(array[i], 0, array[i], n/2, n);
			Arrays.fill(array[i], 0, n/2, SPACE);
		}
		
		return;
	}
}
