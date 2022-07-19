import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_����_1202_��������_���2_������_2964ms
 * 
 *  1. ���̵��
 *    - ���� ����� �������� ���濡 ä���ִ´�. 
 *      : ����, O(n logn)
 *    - ������ ���� �� �ִ� ���� ��, ���� ������ ���濡 ä���ִ´�.
 *      : �Ű����� Ž��, O(n logn) / ����, O(n logn)
 *    - ä������ ������ �����Ѵ�. 
 * 
 */
public class Main_����_1202_��������_���2_������_2964ms {
	
	static class Jewel implements Comparable<Jewel> {
		int weight;
		int price;
		
		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Jewel o) {
			return o.price - this.price;
		}
	}
	
	static class Bag implements Comparable<Bag>{
		int maxWeight;
		Jewel jewel;
		
		public Bag(int maxWeight) {
			this.maxWeight = maxWeight;
		}

		@Override
		public int compareTo(Bag o) {
			return o.maxWeight - this.maxWeight;
		}
	}
	
	private static List<Jewel> jewels;
	private static List<Bag> bags;
	
	private static long total;
	private static int N;
	private static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 1 <= N, K <= 300,000
		K = Integer.parseInt(st.nextToken());
		
		jewels = new ArrayList<Jewel>(N);
		bags = new ArrayList<Bag>(K);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int weight = Integer.parseInt(st.nextToken());
			int price  = Integer.parseInt(st.nextToken());
			
			jewels.add(new Jewel(weight, price));
		}
		
		for (int i = 0; i < K; i++) {
			int maxWeight = Integer.parseInt(br.readLine());
			
			bags.add(new Bag(maxWeight));
		}
		
		Collections.sort(jewels);
		Collections.sort(bags);
		
		for (Jewel jewel : jewels) {
			if (K == 0)
				break;
			paramSearch(jewel);
		}
		
		System.out.println(total);
	}
	
	public static void paramSearch(Jewel jewel) {
		
		int start = 0;
		int end = K-1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int maxWeight = bags.get(mid).maxWeight;
			int jewelWeight = jewel.weight;
			
			// ������ ������ ���
			if (mid + 1 == K) {
				if (maxWeight >= jewelWeight) {
					total += jewel.price;
					bags.remove(mid);
					K--;
				}
				break;
			}
			
			// mid�� �����ϴ� ���濡, jewel�� ���� �� �ִ� ���
			if (maxWeight >= jewelWeight) {
				if (bags.get(mid+1).maxWeight < jewel.weight) {
					total += jewel.price;
					bags.remove(mid);
					K--;
					break;
				}
				else {
					start = mid + 1;
				}
			}
			
			
			// mid�� �����ϴ� ���濡, jewel�� ���� �� ���� ���
			else {
				end = mid-1;
			}
		}
	}
	
}
