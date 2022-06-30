package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백준_1715_카드정렬하기_G4_김현교_348ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<CardDeck> pq = new PriorityQueue<>();
		
		while (N-- > 0) {
			pq.add(new CardDeck(Integer.parseInt(br.readLine())));
		}
		String a = "";
		
		int sumComp = 0;
		while (pq.size() > 1) {
			CardDeck deck1 = pq.poll();
			CardDeck deck2 = pq.poll();
			
			int nCard = deck1.nCard + deck2.nCard;
			sumComp += nCard;
			pq.add(new CardDeck(nCard));
		}
		
		System.out.println(sumComp);
	}//end main
	
	static class CardDeck implements Comparable<CardDeck>{
		int nCard;

		public CardDeck(int nCard) {
			this.nCard = nCard;
		}
		
		@Override
		public int compareTo(CardDeck o) {
			return nCard - o.nCard;
		}
	}
}
/*
 * 가장 작은 수끼리 더해야 함.
 * 
 * 
 * 10 20 40 80 150
 * 
 * 30 120 180 300 / 630
 * 
 * 30 230 70 300 / 630
 * 
 * 30 70 150 300 /
 * 
 * 30 60 100 150 / 340
 * 
 * 30 70 80 150 / 330
 * 
 * 30 90 60 150 / 330
 * 
 * */
