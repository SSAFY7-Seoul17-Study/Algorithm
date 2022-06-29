package problems.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_11000_강의실배정_골5_추준성_768ms {
	/*
	 * <설계>
	 * 0. O(N)으로 설계해야함
	 * 1. 시작 시각 순으로 강의 오름차순 정렬 (== 시간 순으로 고려)
	 * 2. 최대한 빨리 종료된 강의 바로 뒤에 붙게 고려되게끔 해야됨 
	 * - 종료 시각 기준으로 PQ 생성
	 * - "가장 빠른 종료 시각 <= 강의 시작 시각"이면 PQ에서 poll
	 * 3. PQ의 크기가 배정된 강의실의 최소 개수
	 * 
	 */
	
	static class Lecture{
		int start;
		int end;
		public Lecture(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Integer> pQueue = new PriorityQueue<>(); // 종료 시각 관리 (종료 시각 오름차순 정렬)
		Lecture[] lectures = new Lecture[N]; // 강의 시간 관리
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures[i] = new Lecture(start, end);
		}
		
		// 시작 시각 순 오름차순 정렬 (만약 시작 시각이 같으면 일찍 끝나는 순)
		Arrays.sort(lectures, (o1, o2) -> o1.start - o2.start != 0 ? o1.start - o2.start : o1.end - o2.end);
		
		pQueue.add(lectures[0].end); // 첫 번째 강의실의 종료 시각
		
		for (int i = 1; i < N; i++) {
			if(lectures[i].start >= pQueue.peek()) pQueue.poll(); // 가장 일찍 끝나는 강의의 종료 시각보다 크거나 같으면, 해당 종료 시각을 pQueue에서 빼냄
			pQueue.add(lectures[i].end); // 강의를 하나씩 고려하면서 종료 시각을 pQueue에 담음
		}
		
		System.out.print(pQueue.size()); // PQ에 남아있는 원소의 개수가 곧 배정된 강의실의 최소 개수
	} // end of main

} // end of class
