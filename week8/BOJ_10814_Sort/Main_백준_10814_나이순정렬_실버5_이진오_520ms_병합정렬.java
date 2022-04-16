import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_10814_나이순정렬_실버5_이진오_520ms
 * 
 *  - 의의
 *    : API를 사용하지 않고, 직접 구현하기
 *    : O(n logn)의 시간 복잡도를 요구
 *    
 *  - 구현
 *    : 병합 정렬
 * 
 */
public class Main_백준_10814_나이순정렬_실버5_이진오_520ms_병합정렬 {
	
	static class Member {
		int age;
		String name;
		public Member(int age, String name) {
			this.age = age;
			this.name = name;
		}
		@Override
		public String toString() {
			return age + " " + name;
		}
	}

	private static Member[] members;
	
	/**	병합 정렬(Merge sort)
	 *    - 시간 복잡도 = O(n logn)
	 *    - 공간 복잡도 = O(n)
	 */
	public static void sort(int start, int end, Member[] temp) {
		if (start + 1 == end)
			return;
		
		// 분할
		int mid = (start + end) / 2;
		sort(start, mid, temp);
		sort(mid, end, temp);
		
		// 정복
		int l = start;
		int r = mid;
		int idx = start;
		while (l < mid && r < end) {
			if (members[l].age <= members[r].age) {
				temp[idx++] = members[l++];
			}
			else {
				temp[idx++] = members[r++];
			}
		}
		while (l < mid) {
			temp[idx++] = members[l++];
		}
		while (r < end) {
			temp[idx++] = members[r++];
		}
		
		for (int i = start; i < end; i++) {
			members[i] = temp[i];
		}
		
	} // end of sort

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine()); // N <= 100,000
		
		members = new Member[N];
		
		for (int i = 0; i < members.length; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			members[i] = new Member(age, name);
		}
		
		sort(0, members.length, new Member[N]);
		
		for (Member member : members) {
			sb.append(member).append("\n");
		}
		
		System.out.print(sb.toString());
		
	} // end of main
	
} // end of class
