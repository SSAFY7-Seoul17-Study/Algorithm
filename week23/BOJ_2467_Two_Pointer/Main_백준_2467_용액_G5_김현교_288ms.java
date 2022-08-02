package ����.TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_����_2467_���_G5_������_288ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] properties = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			properties[i] = Integer.parseInt(st.nextToken());
		}
		
		int leftIdx = 0;
		int rightIdx = N - 1;
		int saveLeftProperty = properties[leftIdx];
		int saveRightProperty = properties[rightIdx];
		int minAbs = Integer.MAX_VALUE;
		while (N-- > 0) {
			int sum = properties[leftIdx] + properties[rightIdx];
			if (Math.abs(sum) < minAbs) {
				minAbs = Math.abs(sum);
				saveLeftProperty = properties[leftIdx];
				saveRightProperty = properties[rightIdx];
			}
			//���� ��ȣ�̸� �ϳ� ���ϰ� ����
			if (leftIdx + 1 != rightIdx &&
					sameSign(properties[leftIdx], properties[rightIdx])) {
				if (properties[leftIdx] < 0)
					leftIdx = rightIdx - 1;
				else
					rightIdx = leftIdx + 1;
				continue;
			}
			if (sum > 0) rightIdx--;
			else if (sum < 0) leftIdx++;
			
			if (sum == 0 || leftIdx == rightIdx) break;
		}
		System.out.println(saveLeftProperty + " " + saveRightProperty);
	}//end main
	
	static boolean sameSign(int left, int right) {
		return (left < 0 && right < 0) || (left >= 0 && right >= 0);
	}
}//end class
/*
 * �� ������ �����̴�.
 * 
 * �� �� ���� ���� ����� ������ �����͸� ��������, ������ ���� �����͸� �������� �Ѵ�.
 * ��� ���� ���� ��갪���� ���밪�� ������ �� 2�� ����
 * [����]
 * 0�̸� �ٷ� ���
 * �� �� ���� ��ȣ�� ������?
 * -> ������ ������ �������� 2���� ����ϰ� ����
 * -> ����� ���� �������� 2�� ��� �� ����
 * 
 * 
 * */
