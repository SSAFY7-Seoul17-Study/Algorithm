package ����.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_����_1202_��������_G2_������_176ms {
	
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
 * ������ N�� ������ K��
 * �� ������ ����M, ����V
 * �� ������ �ִ� ���Դ� C, ���濡�� ���� �� ���� ���� �� �ִ�.
 * 
 * [Ǯ��]
 * �� �������� ������ �� �ִ� �ִ� ������ ���� ����
 * ������ ��� ���԰� �ּ��� ������ ���� ��
 * 
 * �־��� ���� ������ ũ�Ƿ� �ӵ��� ����ȭ�� �ʿ���
 * ������ C�� �������� ����, ������ �������� 
 * 
 * ������ ���� ���� ���� + ���� ���� ����
 * 
 * 100�� 3kg, 30�� 7kg, 50�� 8kg, 80�� 10kg
 * 
 * ������ ��Ѽ� ����
 * ��� �������� ���� Ž�� -> ������ C�� ����M���� ���ų�Ŀ���� -> �� ���ų� ū ������ ���� ã�ƾ� ��
 * ������ ���� �� ���� �� BinarySearch�ϸ� ��
 * 
 * 
 * 
 * */
