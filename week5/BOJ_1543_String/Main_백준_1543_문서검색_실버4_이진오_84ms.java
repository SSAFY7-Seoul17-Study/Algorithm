package baekjoon.i;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * text 내에 존재하는 patt 찾기 - KMP 알고리즘
 * 
 *   1) pi 생성 (pattern match table, 부분 일치 테이블)
 *     : pi[n] = x such that (patt[0...x] == patt[n-x...n]) 
 *     
 *   2) partial match table을 통해, text 내 patt 존재 여부 확인
 *     - text의 인덱스 i / patt의 인덱스 matched
 *       : i는 계속 1씩 증가
 *       : matched는 patt[matched] == text[i]일 경우1씩 증가
 *         => text[i] != patt[matched]일 경우, partial index table을 참조
 *         => pi[matched-1] = x such that (patt[0...x] == patt[matched-x...matched])
 *         => patt[0...x]와 text[i-x...i]는 항상 일치한다, patt[x+1]과, text[i]를 비교
 *         => 일치하지 않을 경우, 반복한다. 
 *
 */
public class Main_백준_1543_문서검색_실버4_이진오_84ms {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] text = in.readLine().toCharArray();
		char[] patt = in.readLine().toCharArray();
		
		int[] pi = new int[patt.length];
		
		int matched = 0;
		for (int i = 1; i < pi.length; i++) {
			while (matched > 0 && patt[i] != patt[matched])
				matched = pi[matched-1];
			if (patt[i] == patt[matched])
				pi[i] = ++matched;
		}
				
		int cnt = 0;
		matched = 0;
		for (int i = 0; i < text.length; i++) {
			while (matched > 0 && text[i] != patt[matched])
				matched = pi[matched-1];
			if (text[i] == patt[matched]) {
				if (++matched == patt.length) {
					matched = 0;
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
