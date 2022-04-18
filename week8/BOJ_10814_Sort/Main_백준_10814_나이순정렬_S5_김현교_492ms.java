package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 병합정렬 사용
 */

public class Main_백준_10814_나이순정렬_S5_김현교_492ms {
	
	public static void main(String[] args) throws Exception {
		MemberList memberList = initMemberList();
		
		MemberInfo[] listSortedByAge =  memberList.sortByAge();
		
		printList(listSortedByAge);
	}//end main

	private static MemberList initMemberList() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int memberSize = Integer.parseInt(br.readLine());
		
		MemberInfo[] members = new MemberInfo[memberSize];
		for (int i = 0; i < memberSize; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			members[i] = new MemberInfo(age, name);
		}
		
		return new MemberList(memberSize, members);
	}
	
	private static void printList(MemberInfo[] sortedByAge) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sortedByAge.length; i++)
			sb.append(sortedByAge[i].age).append(" ")
			.append(sortedByAge[i].name).append("\n");
		System.out.print(sb.toString());
	}
	
	static class MemberList {
		private int memberSize;
		private MemberInfo[] members;
		
		public MemberList(int memberSize, MemberInfo[] members) {
			this.memberSize = memberSize;
			this.members = members;
		}
		
		public MemberInfo[] sortByAge() {
			MemberInfo[] copyList = new MemberInfo[memberSize];
			mergeSort(0, memberSize - 1, copyList);
			return copyList;
		}
		
		void mergeSort(int left, int right, MemberInfo[] copy) {
			if (left == right)
				return;
			
			int mid = (left + right) / 2;
			mergeSort(left, mid, copy);
			mergeSort(mid + 1, right, copy);
			merge(left, right, mid, copy);
		}

		void merge(int left, int right, int mid, MemberInfo[] copy) {
			int l = left;
			int r = mid + 1;
			int copyIdx = left;
			
			while (l <= mid && r <= right)
				copy[copyIdx++] = (members[l].age <= members[r].age) ? members[l++] : members[r++];
			
			int remainIdx = l > mid ? r : l;
			for (int i = copyIdx; i <= right; i++)
				copy[i] = members[remainIdx++];
			
			for (int i = left; i <= right; i++)
				members[i] = copy[i];
		}
	}
	
	static class MemberInfo {
		int age;
		String name;
		
		public MemberInfo(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}
}//end class
