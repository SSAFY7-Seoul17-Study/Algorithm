package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_11000_강의실배정_G5_김현교_580ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Time> lectures = new ArrayList<>(N);
		PriorityQueue<ClassRoom> minHeap = new PriorityQueue<>(N);
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			lectures.add(new Time(start, end));
		}
		Collections.sort(lectures);
		
		minHeap.add(new ClassRoom(0));
		for (Time cur : lectures) {
			if (minHeap.peek().endTime > cur.start) { //강의 시간이 겹치는 경우
				minHeap.add(new ClassRoom(cur.end)); //classRoom 추가
			} else {
				ClassRoom room = minHeap.poll();
				room.endTime = cur.end;
				minHeap.add(room);
			}
		}

		System.out.println(minHeap.size());
	}//end main
	
	static class Time implements Comparable<Time> {
		int start, end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Time o) {
			return start - o.start;
		}
	}
	
	static class ClassRoom implements Comparable<ClassRoom> {
		int endTime;

		public ClassRoom(int endTime) {
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(ClassRoom o) {
			return endTime - o.endTime;
		}
	}
}
/*
 * 한 강의실에 가능한 최대 수업 개수 -> 끝나는 시간 정렬 -> 그리디
 * 
 * 최소의 강의실로 수업 진행
 *  -> 한 강의실에서 최대로 수업을 진행하는 것이 답이 아닐 수 있다.
 *  -> 시간이 겹치는 것은 무조건 다른 강의실을 배정해야 함!!
 * 	-> 시작 시간 기준으로 정렬
 * 	-> 다음 강의 시작 시간이 이전 강의 종료 시간보다 작으면 -> 강의실 추가!
 *  -> 이전 강의는 종료시간을 저장, 다음 강의는 현재 거, 처음 강의실부터 탐색
 * 
 * */
