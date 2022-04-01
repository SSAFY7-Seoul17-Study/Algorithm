import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1543_문서검색_S4_김인태_80ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String doc = br.readLine();
		String word = br.readLine();
		
		int cnt = 0;
		while(doc.length() > 0) {
			if(doc.startsWith(word)) {
				cnt++;
				doc = doc.substring(word.length());
			}else {
				doc = doc.substring(1);
			}
		}
		
		System.out.println(cnt);
	}

}
