package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 설명 : https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
 * 
 * LCS2차원 배열의 의미 : s1의 i, s2의 j까지 탐색했을 때 가진 최장 공통 부분수열 크기를 저장
 * 점화식 : if (s1[i] == s2[j])
 * 			LCS[i][j] = LCS[i - 1][j - 1] + 1
 * 			(현재 위치에서 최장 수열의 시작점임이 보장되는 곳이 대각선 위임)
 * 		 else
 * 			LSC[i][j] = max(LCS[i - 1][j], LCS[i][j - 1])
 * 			(지금까지 탐색한 최장 공통 부분수열의 값을 이어서 저장해감)
 * 			(현재 위치에서 두 문자가 같지 않아도 LCS값은 바뀌지 않으므로)
 * 
 * DP에서 최장 공통 부분수열의 문자 찾는 방법
 *  - 2차원 배열의 마지막 원소부터 탐색
 *  - 현재 위치값이 LCS[i - 1][j] 또는 LCS[i][j - 1] 와 같다면 그 지점으로 이동
 *  	(해당 위치는 공통 부분수열 값이 증가한 부분이 아니라는 의미, 점화식의 역조건을 생각)
 *  - 위의 조건에 해당하지 않으면 그 위치의 문자를 result배열에 채워준다.
 * */

public class Main_백준_9252_LCS2_G4_김현교_108ms {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr1 = br.readLine().toCharArray();
		char[] arr2 = br.readLine().toCharArray();
		
		int[][] LCS = new int[arr1.length + 1][arr2.length + 1];
		
		for (int i = 1; i <= arr1.length; i++) {
			for (int j = 1; j <= arr2.length; j++) {
				if (arr1[i - 1] == arr2[j - 1])
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				else
					LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
			}
		}
		
		int len = LCS[arr1.length][arr2.length];
		char[] result = new char[len];
		
		int idx = len - 1;
		int i = arr1.length;
		int j = arr2.length;
		while (true) {
			int cur = LCS[i][j];
			if (cur == 0) break;
			
			if (LCS[i][j - 1] == cur)
				j--;
			else if (LCS[i - 1][j] == cur)
				i--;
			else {
				result[idx--] = arr1[--i];
				j--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(len).append("\n");
		for (int k = 0; k < len; k++) {
			sb.append(result[k]);
		}
		sb.append("\n");
		System.out.print(sb.toString());
	}
}
/*
 * 첫 수열의 각 알파벳에 순서를 넣어줌(list)
 * 
 * 다음 수열에 각 알파벳마다 넣어준 순서값을 넣어줌(여러개면 앞의 숫자들보다 큰 숫자 중 작은 숫자를 넣음)
 * 
 * 1 3 2 3 4 2 라고 했을 때 최대는 1 2 3 4이다.
 * 
 * 2 1 6 2 1 5
 * 
 * 각 원소 위치에서 앞의 숫자들을 확인
 * 
 * 나보다 작은 숫자 만나면 그 숫자 + 1을 저장
 * 
 * 아니면 그냥 1 저장
 * 
 * 숫자가 가장 큰 값을 max값으로 저장
 * 
 * -----------------
 *  이 작업을 n번 해줘야 함
 * 1. 첫 문자가 2번째 수열에 있는지 확인
 * 2. 있으면 그 위치를 dp[그문자]에 위치값 넣음
 * 3. 다음 문자가 그 위치 다음부터 2번째 수열에 있는지 확인
 * 4. 있으면 dp[다음문자]에 그 위치 넣음
 * 5. 넣을 때 dp에 이미 값이 있으면 넣지 않음
 * */
