package 백준.TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_3078_좋은친구_G4_김현교_300ms {
	
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
 * 이름의 길이가 같아야 좋은 친구
 * 등수 차이가 k이하여야함
 * 슬라이딩 윈도우 기법 사용하여 k등수 이내에 이름의 길이가 같은 쌍을 찾으면 끝~~
 * 
 * 1번인덱스에서 뒤로 k까지 2번 ... 를 세면 개수를 확인하는 중복이 너무 많이 발생
 * 
 * List<Queue>에 길이를 Key, 등수를 value로 K범위만큼 넣어줌
 * 처음 값을 key로 찾아서 values의 길이를 카운트에 추가
 * 두번 째 값을 key로 value에서 하나 빼고 마지막 값을 key로 value에 추가
 * 반복~~
 * 
 * 쌍의 개수의 합이 int범위를 넘을 수 있음!!!
 * 
 * */
