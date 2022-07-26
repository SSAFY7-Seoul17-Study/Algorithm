package ����.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_����_2513_���й���_G3_������_316ms {
	
	private static int N, K, S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		Complex[] complexs = new Complex[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dist = Integer.parseInt(st.nextToken());
			int studentCnt = Integer.parseInt(st.nextToken());
			
			complexs[i] = new Complex(dist - S, studentCnt);
		}
		
		List<Complex> leftList = new ArrayList<>(N / 2);
		List<Complex> rightList = new ArrayList<>(N / 2);
		for (int i = 0; i < N; i++) {
			if (complexs[i].dist < 0) {
				complexs[i].dist *= -1;
				leftList.add(complexs[i]);
			} else {
				rightList.add(complexs[i]);
			}
		}
		Collections.sort(leftList);
		Collections.sort(rightList);
		
		int sum = 0;
		sum += calcDistance(leftList);
		sum += calcDistance(rightList);
		System.out.println(sum);
	}//end main
	
	private static int calcDistance(List<Complex> list) {
		int complexLen = list.size();
		int finishCnt = 0;
		
		int dist = 0;
		while (finishCnt < complexLen) {
			int studentsInBus = 0;
			dist += 2 * list.get(finishCnt).dist;
			for (int i = finishCnt; i < complexLen; i++) {
				if (K - studentsInBus >= list.get(i).studentCnt) {
					studentsInBus += list.get(i).studentCnt;
					list.get(i).studentCnt = 0;
				} else {
					list.get(i).studentCnt -= K - studentsInBus;
					break;
				}
			}
			
			while (finishCnt < complexLen && list.get(finishCnt).studentCnt == 0) {
				finishCnt++;
			}
		}
		return dist;
	}

	static class Complex implements Comparable<Complex> {
		private int dist;
		private int studentCnt;

		public Complex(int dist, int studentCnt) {
			this.dist = dist;
			this.studentCnt = studentCnt;
		}
		
		@Override
		public int compareTo(Complex o) {
			return o.dist - dist;
		}
	}
}//end class
/*
 * �б��� �Դ� ���ٸ� �ּ�ȭ�ؾ���
 * ������ �ָ��ִ� ���� �л����� �¿��;� ��
 * ���ƿ��鼭 ������ ������ ���� �¿���
 * 
 * ������ Ŭ������ -> ���� �� ���� �л� ��, �б����� �Ÿ��� ����
 * 			  -> �б����� �Ÿ��� ���� ����
 * 			-> �ٸ�, ������ 2���̹Ƿ� �̸� ��������� �� (leftList, RightList)
 * 
 * ������ ������list �տ������� Ž�� -> ���� ������ŭ �տ������� ��
 * -> 0�̸� �������� �Ѿ�� ī������ �ؼ� leftCnt, RightCnt��ŭ á���� �̵� ����
 * 
 * */
