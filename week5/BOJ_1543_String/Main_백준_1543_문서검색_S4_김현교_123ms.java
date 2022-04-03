package StringSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_백준_1543_문서검색_S4_김현교_123ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] docs = br.readLine().toCharArray();
		char[] word = br.readLine().toCharArray();
		
		int dLen = docs.length;
		int wLen = word.length;
		
		int[] pi = new int[wLen];
		
		for (int i = 1, j = 0; i < wLen; i++) {
			while (j > 0 && word[j] != word[i])
				j = pi[j - 1];
			if (word[j] == word[i])
				pi[i] = ++j;//같은거는 볼필요 없으므로 다음으로 바로 넘어갈 수 있게 해줌
			else
				pi[i] = 0;
		}
		
		int cnt = 0;
		for (int i = 0, j = 0; i < dLen; i++) {
			while (j > 0 && docs[i] != word[j])
				j = pi[j - 1];
			if (docs[i] == word[j])
				j++;
			else
				j = 0;
			if (j == wLen) {
				j = 0;
				cnt++;
			}
		}
		System.out.println(cnt);
	}//end main
}
/*
 * kmp
 * i는 찾을 문자열의 접미사 포인터
 * j는 패턴의 접두사 포인터
 * 접미사와 접두사가 다른값이면
 * 패턴의 접두사 포인터를 다시 앞으로 당김
 * 
 * */