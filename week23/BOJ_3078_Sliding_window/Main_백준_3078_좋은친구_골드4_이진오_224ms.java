import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_����_3078_����ģ��_���4_������_224ms
 * 
 *  - �����̵� ������
 * 
 */
public class Main_����_3078_����ģ��_���4_������_224ms {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// �̸� ���� �迭
		int[] students = new int[N];
		
		for (int i = 0; i < N; i++) {
			students[i] = br.readLine().length();
		}
		
		// window[l] = ���� K �迭���� �л��� ���� ������ ������
		int[] window = new int[21];
		
		// window �ʱ�ȭ �κ�
		for (int i = 0; i < K; i++) {
			window[students[i]]++;
		}
		
		long pairs = 0;
		
		for (int l = 2; l <= 20; l++) {
			pairs += (long) window[l] * (window[l] - 1) / 2;
		}
		
		// sliding �κ�
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
