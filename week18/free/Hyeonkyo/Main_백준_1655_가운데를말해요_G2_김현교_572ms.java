package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백준_1655_가운데를말해요_G2_김현교_572ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> {
			return o2 - o1;
		});
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> {
			return o1 - o2;
		});	
		
		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			
			if (maxHeap.size() != minHeap.size()) minHeap.add(num);
			else maxHeap.add(num);
			
			if (maxHeap.size() > 0 && minHeap.size() > 0 && 
					maxHeap.peek() > minHeap.peek()) {
				swapRootNode(maxHeap, minHeap);
			}
			
			sb.append(maxHeap.peek()).append("\n");
		}
		
		System.out.print(sb.toString());
	}//end main

	private static void swapRootNode(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
		int tmp = maxHeap.poll();
		maxHeap.add(minHeap.poll());
		minHeap.add(tmp);
	}
}
/*
 * 숫자 in -> 정렬 -> 인덱스 상 가운데 값 출력
 * 
 * pq에 숫자 in -> 자동정렬 -> length / 2 만큼 poll한 값 출력 (다시 넣어주기)
 * 
 * -----------------------------------------------------------------
 * @See : https://dragon-h.tistory.com/6
 * pq 2개 (max, min Heap)
 * 
 * x x x o o o
 * 중간부터 앞의 x들은 maxHeap으로, 뒤의 o들은 minHeap에 담는다.
 * 
 * 매번 입력되는 숫자들을 두 힙의 사이즈가 같으면 max쪽 heap에, 다르면 min쪽 heap에 넣어준다.
 * 두 heap의 루트값만 비교해서 바꿔주면 된다.
 * 
 * */
