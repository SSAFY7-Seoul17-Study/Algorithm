package 백준.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_2513_통학버스_G3_김현교_316ms {
	
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
 * 학교로 왔다 갔다를 최소화해야함
 * 가능한 멀리있는 곳의 학생부터 태워와야 함
 * 돌아오면서 여유가 있으면 쭉쭉 태워줌
 * 
 * 단지를 클래스로 -> 단지 내 현재 학생 수, 학교와의 거리를 가짐
 * 			  -> 학교와의 거리로 내림 정렬
 * 			-> 다만, 방향이 2개이므로 이를 구분해줘야 함 (leftList, RightList)
 * 
 * 버스가 단지들list 앞에서부터 탐색 -> 버스 정원만큼 앞에서부터 뺌
 * -> 0이면 다음으로 넘어가고 카운팅을 해서 leftCnt, RightCnt만큼 찼으면 이동 종료
 * 
 * */
