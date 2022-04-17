import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10814_나이순정렬_실5_추준성_476ms {
	/*
	 * 
	 * 안정 정렬 : 정렬을 한 뒤에도 기존의 순서 유지가 보장되는 정렬
	 * - 버블 정렬, 삽입 정렬, 병합 정렬
	 * 불안정 정렬 : 정렬을 한 뒤에도 기존의 순서 유지가 보장되지 않는 정렬
	 * - 퀵 정렬, 힙 정렬, 선택 정렬
	 * 
	 * - N <= 100,000이고 문제에서 나이가 같을 때도 고려해야 하므로 O(N*logN)이고 안정정렬인 정렬을 사용해야 함
	 * 
	 * 병합정렬 : O(N*logN)
	 */
	static class Member {
		int age;
		String name;
		
		public Member(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}
	}

	private static int N;
	private static Member[] sorted;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		Member[] members = new Member[N];
		sorted = new Member[N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			members[i] = new Member(age, name);
		}
		
		mergeSort(members, 0, N-1);
		
		
		for (int i = 0; i < N; i++) {
			sb.append(members[i].age).append(" ").append(members[i].name).append("\n");
		}
		
		System.out.print(sb.toString());
		
	} // end of main
	
	static void mergeSort(Member[] members, int start, int end) {
		if(start == end) return;
		// 1. 구간을 절반으로 쪼개며 재귀적으로 mergeSort 실행
		int mid = (start + end) / 2;
		mergeSort(members, start, mid);
		mergeSort(members, mid+1, end);
		
		// 2. 이전 stack에서 [start, mid], [mid+1, end]에서 정렬된 정보를 토대로 병합 정렬
		merge(members, start, mid, end);
	}
	
	static void merge(Member[] members, int start, int mid, int end) {
		// 1. members 배열에서 사용할 인덱스 정의
		int i = start;
		int j = mid+1;
		
		// 2. sorted 배열에서 사용할 인덱스 정의
		int k = start; 
		
		// 3. 투 포인터를 활용, 정렬된 두 구간의 데이터를 모두 고려하여 병합 정렬
		while(i <= mid && j <= end) {
			if(members[i].age <= members[j].age) {
				sorted[k] = members[i];
				i++;
			} else {
				sorted[k] = members[j];
				j++;
			}
			k++;
		}

		// 4. 한 구간이 모두 사용됐으면 나머지 한 구간의 데이터롤 sorted 배열 채움
		while(i <= mid) {
			sorted[k] = members[i];
			i++;
			k++;
		}

		while(j <= end) {
			sorted[k] = members[j];
			j++;
			k++;
		}
		
		// 5. 정렬된 배열인 sorted 정보를 members로 업데이트
		for (int idx = start; idx <= end; idx++) {
			members[idx] = sorted[idx];
		}
		
	}
	
} // end of class
