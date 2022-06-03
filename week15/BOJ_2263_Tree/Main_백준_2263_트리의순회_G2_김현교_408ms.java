package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Preorder(전위 순회) : root -> left -> right
 * Inorder(중위 순회) : left -> root -> right
 * Postorder(후위 순회) : left -> right -> root
 *
 * [풀이1]
 * 순회 2개 중 하나 선택해서 이진트리 만들고 preorder로 출력하기
 * 	1. 이진트리 n크기로 만들기
 * 	2. 그 이진트리를 후위순회, 출력 대신 입력을 받음
 * 	3. 입력으로 후위순위로 들어온 값을 넣어줌
 * 	4. 그렇게 채운 이진트리를 전위순회로 출력
 * => 이 경우는 완전 이진트리인 경우만 올바르게 작동함.
 * 
 * [풀이2]
 * 후위, 중위 순회를 비교해서 적절한 트리 위치를 찾아낼 수 있어야 함.
 * 
 * 후위순회 : 좌우 콜스택 이후에 루트 값을 채움
 * 중위순회 : 좌 콜스택 이후 루트값 채우고 우 콜스택 호출
 * 
 * 후위 순회에서는 가장 오른쪽 값이 root
 * 중위 순회에서는 root값 기준 왼쪽 트리, 오른쪽 트리임을 알 수 있다.
 * 
 * 1. 후위 순회에서 해당 트리의 root찾기
 * 2. 해당 트리의 왼쪽 트리와 오른쪽 트리 분할 정복
 * 3. 각 절차에서 구한 root값이 Preorder의 출력값이된다.
 * 
 */

public class Main_백준_2263_트리의순회_G2_김현교_408ms {

	static int N, preIdx;
	static int[][] order;
	static int[] index;
	static StringBuilder sb = new StringBuilder();
	
	static final int IN = 0;
	static final int POST = 1;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		order = new int[2][N + 1];
		index = new int[N + 1];
		
		StringTokenizer stIn = new StringTokenizer(br.readLine());
		StringTokenizer stPost = new StringTokenizer(br.readLine());
		for (int j = 1; j <= N; j++) {
			order[IN][j] = Integer.parseInt(stIn.nextToken());
			index[order[IN][j]] = j;

			order[POST][j] = Integer.parseInt(stPost.nextToken());
		}
		
		getPreorder(1, N, 1, N);
		System.out.println(sb.toString());
	}//end main

	private static void getPreorder(int iStart, int iEnd, int pStart, int pEnd) {
		if (iStart > iEnd || pStart > pEnd)
			return;
		int root = order[POST][pEnd];
		sb.append(root + " ");
		
		int divIdx = index[root];
		
		int leftLen = divIdx - iStart;
		getPreorder(iStart, divIdx - 1, pStart, pStart + leftLen - 1);
		getPreorder(divIdx + 1, iEnd, pStart + leftLen, pEnd - 1);
	}
}//end class









