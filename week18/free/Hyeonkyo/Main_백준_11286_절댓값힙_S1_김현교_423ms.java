package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백준_11286_절댓값힙_S1_김현교_423ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			if (Math.abs(o1) == Math.abs(o2))
				return o1 - o2;
			return Math.abs(o1) - Math.abs(o2);
		});
		
		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			
			if (num == 0) {
				Integer ret = pq.poll();
				if (ret == null) ret = 0;
				
				sb.append(ret).append("\n");
				continue;
			}
			pq.offer(num);
		}
		System.out.print(sb.toString());
	}//end main
}