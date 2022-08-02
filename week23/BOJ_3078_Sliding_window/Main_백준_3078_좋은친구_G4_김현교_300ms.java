package ����.TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_����_3078_����ģ��_G4_������_300ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] lengths = new int[N];
		List<Queue<Integer>> list = new ArrayList<>(21);	
		for (int i = 0; i < 21; i++) {
			list.add(new LinkedList<>());
		}
		
		for (int i = 0; i < N; i++) {
			int nameLength = br.readLine().length();
			lengths[i] = nameLength;
			if (i != 0 && i <= K) {
				list.get(nameLength).offer(i);
			}
		}
		
		long cnt = 0;
		for (int i = 0; i < N - 1; i++) {
			int findLength = lengths[i];
			cnt += list.get(findLength).size();

			int nextIndex = i + 1;
			int nextLength = lengths[nextIndex];
			list.get(nextLength).poll();
			
			int lastIndex = nextIndex + K;
			if (lastIndex < N)
				list.get(lengths[lastIndex]).offer(lastIndex);
		}
		System.out.println(cnt);
	}//end main
}//end class
/*
 * �̸��� ���̰� ���ƾ� ���� ģ��
 * ��� ���̰� k���Ͽ�����
 * �����̵� ������ ��� ����Ͽ� k��� �̳��� �̸��� ���̰� ���� ���� ã���� ��~~
 * 
 * 1���ε������� �ڷ� k���� 2�� ... �� ���� ������ Ȯ���ϴ� �ߺ��� �ʹ� ���� �߻�
 * 
 * List<Queue>�� ���̸� Key, ����� value�� K������ŭ �־���
 * ó�� ���� key�� ã�Ƽ� values�� ���̸� ī��Ʈ�� �߰�
 * �ι� ° ���� key�� value���� �ϳ� ���� ������ ���� key�� value�� �߰�
 * �ݺ�~~
 * 
 * ���� ������ ���� int������ ���� �� ����!!!
 * 
 * */
