package study.day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2263_트리의순회_골2_추준성_388ms {
	private static int[] inOrder;
	private static int[] inOrderIdx;
	private static int[] postOrder;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st1, st2;
		int N = Integer.parseInt(br.readLine());
		inOrder = new int[N+1];
		inOrderIdx = new int[N+1]; // inOrder에서 root의 index를 O(1)로 찾기 위함
		postOrder = new int[N+1];
		
		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			inOrder[i] = Integer.parseInt(st1.nextToken());
			inOrderIdx[inOrder[i]] = i; // inOrder[i]를 입력하면 해당 인덱스를 얻을 수 있음
			postOrder[i] = Integer.parseInt(st2.nextToken());
		}
		
		getPreOrder(1, N, 1, N);
		
		System.out.print(sb.toString());
		
	} // end of main

	private static void getPreOrder(int in_start, int in_end, int post_start, int post_end) {
		if(in_start > in_end || post_start > post_end) return;
		
		// 0. start == end가 될 때까지 root를 inOrder 배열에 담음
		sb.append(postOrder[post_end]).append(" ");
		
		// 1. inOrder의 root 인덱스 찾기
		int in_root = inOrderIdx[postOrder[post_end]];
		
		// 2. 재귀호출
		getPreOrder(in_start, in_root - 1, post_start, post_start + in_root - in_start - 1); // 왼쪽 자식 트리 (in_root - in_start - 1 : 왼쪽 자식 트리의 길이)
		getPreOrder(in_root + 1, in_end, post_start + in_root - in_start, post_end - 1); // 오른쪽 자식 트리
	}

} // end of class
