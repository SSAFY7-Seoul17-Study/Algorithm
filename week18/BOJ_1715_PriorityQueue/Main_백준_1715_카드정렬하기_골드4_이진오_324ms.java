import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * Main_백준_1715_카드정렬하기_골드4_이진오_324ms
 * 
 *  - 우선순위 큐 (Priority Queue)
 *    : 현재 카드 덱의 집합 deck 전체를 합칠 때, 가장 적은 비교 횟수를 통해 전체를 합치는 경우의 수
 *      deck.enqueue(deck.dequeue() + deck.dequeue());
 *      deck을 최소 힙으로 정의
 *        => 탐욕 기법
 *  - 시간 복잡도
 *    : PQ를 활용할 경우 O(n logn), 카드 덱의 크기가 점점 줄어드므로 실제 작동 시간은 더 작을 것으로 예상
 *  - 공간 복잡도
 *    : PQ = O(n) 
 *    
 *  - 오버플로우 확인
 *    : 10E5 * 10E3 = 10E8
 * 
 */
public class Main_백준_1715_카드정렬하기_골드4_이진오_324ms {
	
	private static PriorityQueue<Integer> deck;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		deck = new PriorityQueue<Integer>(N);
		
		for (int i = 0; i < N; i++)
			deck.offer(Integer.parseInt(br.readLine()));
		
		int cnt = 0;
		while (deck.size() > 1) {
			int temp = deck.poll() + deck.poll();
			deck.offer(temp);
			cnt += temp;
		}
		
		System.out.println(cnt);
	}
	
}
