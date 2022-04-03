package Sum_Subsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2042_구간합구하기_G1_김현교_544ms {
	
	static long[] num;
	static int N, TC, treeSize;
	static Node[] tree;
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		1. 수열 입력 받기
		input(br);
		
//		2. 이진트리 만들기
		treeSize = 4 * N;
		tree = new Node[treeSize];//무조건 4N보다 작게나옴
		
//		3. 트리에 값 채우기
		init(1, N, 1);
		
//		4. 연산 수행
		for (int i = 0; i < TC; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			switch (a) {
			case 1: //값 변경하기
				update(b, c);
				break;
			default: //구간 합 구하기
				sb.append(query(b, (int)c, 1)).append("\n");
				break;
			}
		}
		
		System.out.print(sb.toString());
		
	}//end main

	private static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		TC = Integer.parseInt(st.nextToken());
		TC += Integer.parseInt(st.nextToken());
		
		num = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
	}

	/** curNode에 값을 채우는 메서드 */
	static long init(int start, int end, int curNode) {
		
		tree[curNode] = new Node(start, end);
		
		if (start == end) {//leaf Node
			return tree[curNode].value = num[start];
		}
		
		//트리의 자식 노드 먼저 채워주고
		int mid = (start + end) / 2;
		init(start, mid, curNode * 2);
		init(mid + 1, end, curNode * 2 + 1);
		
		//그 자식노드를 더해서 현재 노드 채우기
		return tree[curNode].value = 
				tree[curNode * 2].value + tree[curNode * 2 + 1].value;
	}
	
	/** 구간 [l, r]의 합을 구하는 함수 */
	static long query(int l, int r, int curNode) {

		int start = tree[curNode].start;
		int end = tree[curNode].end;
		
		if (l > end || r < start)//구하려는 구간이 트리의 범위에 아예 해당하지 않는 경우(case1)
			return 0;
		
		if (l <= start && end <= r)//구하려는 구간이 현재 트리의 범위를 아예 포함하는 경우(case2)
			return tree[curNode].value;
		
		//구하려는 구간이 현재 트리의 일정 부분에 속하는 경우(case3)
		long lSum = query(l, r, curNode * 2);
		long rSum = query(l, r, curNode * 2 + 1);
		return lSum + rSum;
	}
	
	static void update(int index, long newValue) {
		long diff = newValue - num[index]; //Diff구해줌
		num[index] = newValue; //수열의 값 변경
		updateTree(index, diff, 1); //값 변경 시작
	}
	
	/** 각 구간에 바꿀 값이 속하면 Diff를 더해서 값 변경해줌 */
	static void updateTree(int index, long diff, int curNode) {
		
		if (curNode >= treeSize || tree[curNode] == null)
			return ;
		
		if (tree[curNode].start <= index && index <= tree[curNode].end) {
			tree[curNode].value += diff;
			updateTree(index, diff, curNode * 2);
			updateTree(index, diff, curNode * 2 + 1);
		}
	}

	static class Node {
		int start, end;
		long value;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
