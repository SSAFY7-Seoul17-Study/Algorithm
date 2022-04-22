import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Main_백준_2217_로프_실버4_이진오_224ms
 * 
 *  - 그리디
 *    1) 그리디라면, 정렬을 생각하자!
 *      : 가장 가벼운 무게 * N
 *      : ...
 *      : 가장 무거운 무게 * 1
 *      
 *      위 N개의 값 중 최대
 * 
 */
public class Main_백준_2217_로프_실버4_이진오_224ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] lopes = new int[N];
		
		for (int i = 0; i < lopes.length; i++) {
			lopes[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(lopes);
		
		int max = 0;
		
		for (int i = 0; i < lopes.length; i++) {
			int w = lopes[i] * (N - i);
			if (w > max)
				max = w;
		}
		
		System.out.println(max);
	}
}
