import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BOJ_1715_카드정렬하기_G4_336ms {

	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(N);
		
		for(int i=0; i<N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int res = 0;
		while(pq.size()>1) {
			int a = pq.poll();
			int b = pq.poll();
			res += a+b;
			pq.add(a+b);
		}
		
		System.out.println(res);
	}

}