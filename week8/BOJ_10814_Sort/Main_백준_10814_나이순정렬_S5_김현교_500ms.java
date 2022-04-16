package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 병합정렬 사용
 */

public class Main_백준_10814_나이순정렬_S5_김현교_500ms {
	
	public static void main(String[] args) throws Exception {
		MemberList memberList = getMemberList();
		
		Member[] sortedByAge =  memberList.sortByAge();
		
		printList(sortedByAge);
	}//end main

	private static MemberList getMemberList() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int memberSize = Integer.parseInt(br.readLine());
		
		Member[] members = new Member[memberSize];
		for (int i = 0; i < memberSize; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			members[i] = new Member(age, name);
		}
		
		return new MemberList(memberSize, members);
	}
	
	private static void printList(Member[] sortedByAge) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sortedByAge.length; i++)
			sb.append(sortedByAge[i].age).append(" ")
			.append(sortedByAge[i].name).append("\n");
		System.out.print(sb.toString());
	}
	
	static class MemberList {
		private int memberSize;
		private Member[] members;
		
		public MemberList(int memberSize, Member[] members) {
			this.memberSize = memberSize;
			this.members = members;
		}
		
		public Member[] sortByAge() {
			Member[] copyList = new Member[memberSize];
			System.arraycopy(members, 0, copyList, 0, memberSize);
			mergeSort(0, memberSize - 1, copyList);
			return copyList;
		}
		
		void mergeSort(int left, int right, Member[] members) {
			if (left == right)
				return;
			
			int mid = (left + right) / 2;
			mergeSort(left, mid, members);
			mergeSort(mid + 1, right, members);
			merge(left, right, mid, members);
		}

		void merge(int left, int right, int mid, Member[] members) {
			int size = right - left + 1;
			Member[] tmp = new Member[size];
			
			int l = left;
			int r = mid + 1;
			int idx = 0;
			while (l <= mid && r <= right) {
				if (members[l].age <= members[r].age)
					tmp[idx++] = members[l++];
				else
					tmp[idx++] = members[r++];
			}
			
			if (l > mid) {
				for (int i = idx; i < size; i++) {
					tmp[i] = members[r++];
				}
			} else if (r > right) {
				for (int i = idx; i < size; i++) {
					tmp[i] = members[l++];
				}
			}
			
			idx = 0;
			for (int i = left; i <= right; i++) {
				members[i] = tmp[idx++];
			}
		}
	}
	
	static class Member {
		int age;
		String name;
		
		public Member(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}
}//end class
