package 백준.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_1202_보석도둑_G2_김현교_176ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Jewel> jewels = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			jewels.add(new Jewel(M, V));
		}
		Collections.sort(jewels, (o1, o2) -> {
			return o2.price - o1.price;
		});
		
		Integer[] limits = new Integer[K];
		for (int i = 0; i < K; i++) {
			limits[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(limits, Collections.reverseOrder());
		
		boolean[] v = new boolean[K];
		long sum = 0;
		for (Jewel jewel : jewels) {
			int weight = jewel.weight;
			
			int idx = Arrays.binarySearch(limits, weight);
			idx = idx >= 0 ? idx : -idx - 1;
			
			while (idx < K && v[idx])
				idx++;
			if (idx >= K) continue;
			
			v[idx] = true;
			sum += jewel.price;
		}
		System.out.println(sum);
	}//end main
	
	static class Jewel {
		private int weight;
		private int price;

		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
	}
	
}//end class
/*
 * 보석이 N개 가방이 K개
 * 각 보석은 무게M, 가격V
 * 각 가방의 최대 무게는 C, 가방에는 보석 한 개만 담을 수 있다.
 * 
 * [풀이]
 * 각 가방으로 선택할 수 있는 최대 가격의 보석 선택
 * 가방의 허용 무게가 최소인 가방을 골라야 함
 * 
 * 주어진 값의 범위가 크므로 속도의 최적화가 필요함
 * 가방은 C로 내림차순 정렬, 보석은 가격으로 
 * 
 * 보석을 무게 내림 정렬 + 가격 내림 정렬
 * 
 * 100원 3kg, 30원 7kg, 50원 8kg, 80원 10kg
 * 
 * 보석을 비싼순 정렬
 * 비싼 보석부터 가방 탐색 -> 가방의 C가 보석M보다 같거나커야함 -> 이 같거나 큰 가방을 빨리 찾아야 함
 * 가방을 무게 순 정렬 후 BinarySearch하면 됨
 * 
 * 
 * 
 * */
