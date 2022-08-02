package 백준.TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2467_용액_G5_김현교_288ms {
	
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
			//같은 부호이면 하나 비교하고 종료
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
 * 투 포인터 문제이다.
 * 
 * 양 끝 값의 합이 양수면 오른쪽 포인터를 안쪽으로, 음수면 왼쪽 포인터를 안쪽으로 한다.
 * 계산 값이 이전 계산값보다 절대값이 작으면 그 2값 저장
 * [예외]
 * 0이면 바로 출력
 * 양 끝 값의 부호가 같으면?
 * -> 음수면 오른쪽 포인터쪽 2개를 계산하고 종료
 * -> 양수면 왼쪽 포인터쪽 2개 계산 후 종료
 * 
 * 
 * */
