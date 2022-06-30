package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백준_1417_국회의원선거_S5_김현교_80ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Candidate> pq = new PriorityQueue<>();
		Candidate[] address = new Candidate[N + 1];
		
		for (int no = 1; no <= N; no++) {
			int numVote = Integer.parseInt(br.readLine());
			Candidate c = new Candidate(no, numVote);
			pq.add(c);
			address[no] = c;
		}
		
		int cnt = 0;
		while (true) {
			Candidate c = pq.poll();
			if (c.no == 1) break;
			cnt++;
			
			c.numVote--;
			address[1].numVote++;
			pq.clear();
			for (int i = 1; i <= N; i++) {
				pq.add(address[i]);
			}
		}
		System.out.println(cnt);
	}//end main
	
	static class Candidate implements Comparable<Candidate> {
		
		private int no;
		private int numVote;
		
		public Candidate(int no, int numVote) {
			this.no = no;
			this.numVote = numVote;
		}
		
		@Override
		public int compareTo(Candidate o) {
			if (o.numVote == numVote)
				return o.no - no;
			return o.numVote - numVote;
		}
	}
}