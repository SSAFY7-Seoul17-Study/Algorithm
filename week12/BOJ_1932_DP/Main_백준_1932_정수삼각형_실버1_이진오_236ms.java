import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_1932_정수삼각형_실버1_이진오_236ms
 * 
 *  - 점화식
 *    : f(index) = tri[index] + Math.max(f(index+floor), f(index+floor+1));
 * 
 *  - dp 하향식
 *    : 메모리 24000KB, 시간 244ms
 *  
 *  - dp 상향식
 *    : 메모리 24000KB, 시간 236ms
 * 
 */
public class Main_백준_1932_정수삼각형_실버1_이진오_236ms {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); // 정수 삼각형의 크기, N <= 500
		
		int size = N * (N+1) / 2 + 1; // size < 대략 120000
		
		int[] tri = new int[size]; // 정수 삼각형
		
		int index = 0;
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			for (int j = 0; j < i; j++) {
				int val = Integer.parseInt(st.nextToken()); // val < 10000
				tri[index++] = val;
			}
		}
		
		for (int i = N; i > 1; i--) {
			int offset = i * (i-1) / 2;
			int l = tri[offset];
			
			for (int j = 1; j < i; j++) {
				int r = tri[offset + j];
				
				int val = (l > r) ? l : r;
				
				tri[offset + j - i] += val;
				
				l = r;
			}
		}
		
		System.out.println(tri[0]);
	}
}
