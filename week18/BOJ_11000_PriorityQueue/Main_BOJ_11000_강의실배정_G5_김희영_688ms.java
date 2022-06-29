package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_11000_강의실배정_G5_김희영_688ms {

	public static class Lecture implements Comparable<Lecture> {
		int start;
		int end;

		Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lecture o) {
			if (this.start != o.start)
				return this.start - o.start;
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Lecture[] lectures = new Lecture[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(lectures);

		PriorityQueue<Integer> rooms = new PriorityQueue<>();
		rooms.offer(0);

		for (int i = 0; i < lectures.length; i++) {
			if (rooms.peek() <= lectures[i].start)
				rooms.poll();
			rooms.offer(lectures[i].end);
		}
		
		System.out.println(rooms.size());

	} // end of main

} // end of class
