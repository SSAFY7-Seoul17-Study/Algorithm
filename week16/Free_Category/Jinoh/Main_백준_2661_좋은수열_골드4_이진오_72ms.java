import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main_백준_2661_좋은수열_골드4_이진오_72ms
 * 
 *  2022-06-12
 * 
 *  - 문제
 *    1) 좋은 수열
 *      : 임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 없는 수열
 *  
 *  - 백트래킹
 *    1) 수열 s가 있을 때, 올 수 있는 다음 숫자를 제한한다. 
 *      : s = 121인 경우, 가능한 다음 숫자는 3 
 *      : s = 1213의 경우, 가능한 다음 숫자는 1 혹은 2
 *      ...
 *    
 *  - 수열 s의 다음 숫자 결정 로직
 *    1) n >= 1 && s(n)을 후보에서 제외
 *    2) n >= 3 && s(n) == s(n-2)인 경우 s(n-1)을 후보에서 제외
 *    3) 길이 l = 3 ~ n/2에 대하여, s[n-2*l...n-l] == s[n-l...n] 중 나쁜 수열이 존재하는지 판별
 * 
 */
public class Main_백준_2661_좋은수열_골드4_이진오_72ms {
	
	private static int N;
	private static char[] seq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		seq = new char[N];
		
		dfs(0);
		
		System.out.println(new String(seq));
	}
	
	public static boolean dfs(int n) {
		if (n == N)
			return true;
		
		// seq[n]에 가능한 숫자 대입
		for (char ch = '1'; ch <= '3'; ch++) {
			if (n >= 1 && seq[n-1] == ch)
				continue;
			if (n >= 3 && seq[n-1] == seq[n-3] && seq[n-2] == ch)
				continue;
			
			seq[n] = ch;
			if (checkBadSeq(n+1))
				continue;
			
			if (dfs(n+1))
				return true;
		}
		return false;
	}
	
	/**
	 * @param n
	 * @return seq[0...n]이 나쁜 수열인지 판별
	 */
	public static boolean checkBadSeq(int n) {
		// 길이 1 혹은 2의 부분 수열은 체크 완료
		if (n < 6)
			return false;
		// 길이 3 이상의 부분 수열을 체크
		boolean isBadSeq = true;
		for (int l = 3; l <= n/2; l++) {
			isBadSeq = true;
			for (int i = l; i > 0; i--) {
				if (seq[n-l-i] != seq[n-i]) {
					isBadSeq = false;
					continue;
				}
			}
			if (isBadSeq)
				return true;
		}
		return false;
	}
	
}
