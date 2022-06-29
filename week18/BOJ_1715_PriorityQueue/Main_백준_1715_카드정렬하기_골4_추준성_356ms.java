package problems.자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백준_1715_카드정렬하기_골4_추준성_356ms {
	/*
	 * <설계>
	 * - O(N^2) 미만 => 이중 for문 사용 X => 힙(PQ) 사용
	 * - 가장 작은 숫자 두 개 꺼내서 합치고 PQ에 다시 넣기
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pQueue.add(Integer.parseInt(br.readLine()));
		}
		
		int sum = 0;
		while(pQueue.size() > 1) {
			// 1. 가장 작은 숫자 두 개 꺼내기
			int num1 = pQueue.poll(); 
			int num2 = pQueue.poll();
			
			// 2. 두 수를 합한 값 tmp를 sum에 누적 & tmp를 PQ에 담기 
			int tmp = num1 + num2;
			sum += tmp;
			pQueue.add(tmp);
		}
		
		System.out.println(sum);
		
	} // end of main

} // end of class
