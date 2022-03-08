import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Main_백준_1700_멀티탭스케줄링_골드1_80ms
 * 
 *  - 아이디어
 *    (1) 그리디
 *      : 다음 사용까지의 시간이 가장 오래 남은 전자기기 poll
 *  
 *  - 자료구조
 *    (1) Electronic 클래스
 *      : on/off - boolean
 *      : schedule - List
 *      : compareTo - 다음 스케쥴 기준 오름차순 정렬
 *      
 *    (2) 배열
 *      : int[] schedules - 사용할 전자기기의 index를 순서대로 저장할 배열
 *      : Electronic[] electronics - 전자기기의 상태를 저장할 배열
 *      : Electronic[] multiTab - 멀티탭에 꽂힌 전자기기를 저장할 배열
 *      : Hmm...
 */
public class Main_백준_1700_멀티탭스케줄링_골드1_80ms {
	
	static class Electronic implements Comparable<Electronic> {
		boolean on;
		LinkedList<Integer> schedule;
		
		public Electronic(LinkedList<Integer> schedule) {
			super();
			this.on = false;
			this.schedule = schedule;
		}
		
		public void add(int i) {
			this.schedule.add(i);
		}
		
		/**
		 * @return 전자기기의 우선 순위 - 다음 스케줄의 index
		 */
		public int getPriority() {
			if (schedule.isEmpty()) return Integer.MAX_VALUE;
			return schedule.peek();
		}

		@Override
		public int compareTo(Electronic o) {
			return this.getPriority() - o.getPriority();
		}
	} // end of class Electronic

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍 수, 1 <= N <= 100
		int K = Integer.parseInt(st.nextToken()); // 사용 횟수, 1 <= K <= 100
		
		// 자료구조
		int[] schedules = new int[K];
		Electronic[] electronics = new Electronic[K+1];
		Electronic[] multiTab = new Electronic[N];
		
		for (int i = 1; i < electronics.length; i++) {
			electronics[i] = new Electronic(new LinkedList<Integer>());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			int e = Integer.parseInt(st.nextToken());
			schedules[i] = e;
			electronics[e].add(i);
		}
		
		// 스케줄에 따라 순차적으로 처리
		int tabs = 0;
		int polls = 0;
		for (int i = 0; i < K; i++) {
			int e = schedules[i];
			Electronic toOffer = electronics[e];
			toOffer.schedule.poll(); // 현재 전자기기 스케줄 리스트에서 지금 스케줄 제거
			
			// 사용중이 아니라면
			if (!toOffer.on) {
				toOffer.on = true;
				// 멀티탭에 빈 자리가 있다면
				if (tabs < N) {
					multiTab[tabs++] = toOffer;
				}
				else {
					// 우선순위가 가장 나중인 탭이, 마지막에 오도록
					Arrays.sort(multiTab);
					Electronic polled = multiTab[N-1];
					polled.on = false;
					polls++;
					multiTab[N-1] = toOffer;
				}
			}
		}
		
		System.out.println(polls);
	} // end of main
	
} // end of class
