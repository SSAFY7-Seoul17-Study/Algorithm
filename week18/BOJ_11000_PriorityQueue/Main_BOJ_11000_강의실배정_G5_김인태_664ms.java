import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_11000_강의실배정_G5_664ms {

	static class Lecture implements Comparable<Lecture>{
		int s;
		int t;
		
		public Lecture(int s, int t) {
			super();
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(Lecture o) {
			if(this.s == o.s) {
				return this.t - o.t;
			}else {
				return this.s-o.s;
			}
		}
		
		
	}
	
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		ArrayList<Lecture> list = new ArrayList<Lecture>(N);
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			list.add(new Lecture(s,t));
		}
	
		Collections.sort(list);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(list.get(0).t);
		
		for(int i=1; i<N; i++) {
			if(pq.peek() <= list.get(i).s) {
				pq.poll();
			}
			pq.add(list.get(i).t);
		}
		
		
		System.out.println(pq.size());
	}

}
