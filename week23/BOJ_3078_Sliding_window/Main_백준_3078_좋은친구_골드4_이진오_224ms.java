import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_3078_좋은친구_골드4_이진오_224ms
 * 
 *  - 슬라이딩 윈도우
 * 
 */
public class Main_백준_3078_좋은친구_골드4_이진오_224ms {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 이름 길이 배열
		int[] students = new int[N];
		
		for (int i = 0; i < N; i++) {
			students[i] = br.readLine().length();
		}
		
		// window[l] = 길이 K 배열에서 학생의 수를 저장할 윈도우
		int[] window = new int[21];
		
		// window 초기화 부분
		for (int i = 0; i < K; i++) {
			window[students[i]]++;
		}
		
		long pairs = 0;
		
		for (int l = 2; l <= 20; l++) {
			pairs += (long) window[l] * (window[l] - 1) / 2;
		}
		
		// sliding 부분
		int idx = K;
		while (idx < N) {
			int noob = students[idx];
			pairs += window[noob]++;
			
			int oldb = students[idx-K];
			window[oldb]--;
			
			idx++;
		}
		
		System.out.println(pairs);
	}

}
