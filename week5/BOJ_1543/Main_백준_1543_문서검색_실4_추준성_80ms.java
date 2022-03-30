import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_1543_문서검색_실4_추준성_80ms {
	/*
	 * 시간 복잡도 : 2500 * 50 = 125000
	 * 
	 * 1. 중복체크 => 문자가 중복되면 안되므로, 검색 시작해서 찾으면, 해당 지점의 끝 인덱스+1 지점부터 재탐색 시작 
	 * 2. 왼쪽부터 검색(검색 문자열 크기의 window를 한 칸씩 증가시키며)을 시작해서 찾으면, 해당 지점의 끝 인덱스+1 지점부터 재탐색 시작 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] document = br.readLine().toCharArray();
		char[] target = br.readLine().toCharArray();
		int docSize = document.length;
		int targetSize = target.length;
		
		// 탐색 성공시 startIdx += targetSize
		// 탐색 실패시 startIdx++
		int startIdx = 0; // 탐색 시작 위치
		int cnt = 0; // 중복되지 않게 등장하는 횟수
		int lastIdx = docSize-targetSize; // while문의 마지막 인덱스 (계산)
		while(startIdx <= lastIdx) {
			
			// target 문자열 길이만큼 반복하며 비교 (단어가 다르면 flag를 false로 바꾸고 탈출)
			boolean flag = true; // 검색 성공
			for (int i = 0; i < targetSize; i++) {
				if(document[startIdx + i] == target[i]) continue;
				else {
					flag = false;
					break;
				}
			}
			
			// 검색 성공시, 중복 안되게끔 targetSize만큼 건너 뛰어서 탐색 시작
			if(flag) {
				cnt++;
				startIdx += targetSize;
			// 검색 실패시, startIdx++
			} else {
				startIdx++;
			}
		}
		
		System.out.print(cnt);
		
	} // end of main

} // end of class
