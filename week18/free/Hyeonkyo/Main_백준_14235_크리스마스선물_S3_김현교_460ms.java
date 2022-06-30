package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_14235_크리스마스선물_S3_김현교_460ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			return o2 - o1;
		});
		
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 0) {
				Integer gift = pq.poll();
				if (gift == null) gift = -1;
				sb.append(gift).append("\n");
				continue;
			}
			for (int i = 0; i < a; i++) {
				pq.add(Integer.parseInt(st.nextToken()));				
			}
		}
		
		System.out.print(sb.toString());
	}//end main
}