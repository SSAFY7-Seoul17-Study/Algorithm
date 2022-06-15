import java.util.Scanner;

/**
 * Main_백준_15649_N과M_1_실버3_176ms
 * 
 * 2022-06-08
 * 
 *  1) Bitmasking - 228ms
 *    : 선택한 숫자 n을 n번째 비트에 마스킹
 * 
 *  2) char[] 적용 - 176ms
 *    : int[] 배열을 하나씩 꺼내서 StringBuilder에 추가 
 *      => char[] 배열을 한 번에 StringBuilder에 추가
 * 
 */
public class Main_백준_15649_N과M_1_실버3_이진오_176ms {
	
	static int N;
	static int M;
	static char[] seq;
	static StringBuilder out;
	
	static void run(int idx, int selected) {
		if (idx == M) {
			// StringBuilder에 결과 추가
			out.append(seq).append("\n");
			return;
		}
		
		for (int i = 1, ch = '1'; i <= N; i++, ch++) {
			// BackTracking
			if ((selected & 1 << i) > 0)
				continue;
			
			seq[idx<<1] = (char) ch;
			run(idx+1, selected | 1 << i);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		out = new StringBuilder();
		
		N = sc.nextInt();
		M = sc.nextInt();
		seq = new char[2*M-1];
		
		for (int i = 1; i < seq.length; i += 2)
			seq[i] = ' ';
		
		run(0, 0);
		
		System.out.print(out.toString());
		
		sc.close();
	}
	
}
