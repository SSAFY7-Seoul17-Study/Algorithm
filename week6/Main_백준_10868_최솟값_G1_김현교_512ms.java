package Sum_Subsequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10868_최솟값_G1_김현교_512ms {
	
	private static int[] tree;
	private static int[] num;

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		num = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		//tree만들기
		tree = new int[4 * N];
		//tree에 값 채우기(각 부분 집합의 최솟값)
		init(1, N, 1);
		
		//연산 수행(범위 주어질때 범위 해당하는 부분끼리 최솟값 비교)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(query(1, N, l, r, 1)).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}//end main

	private static int query(int start, int end, int l, int r, int cur) {
		if (start > r || end < l)
			return Integer.MAX_VALUE;//이 범위는 탐색지점이 아니므로 max값 리턴
		if (l <= start && end <= r)
			return tree[cur];//이 범위의 모든 숫자 중 최솟값을 찾아야 하므로 바로 node값 리턴
		
		int mid = (start + end) / 2;
		int lMin = query(start, mid, l, r, 2 * cur);//왼쪽 구간의 최솟값
		int rMin = query(mid + 1, end, l, r, 2 * cur + 1);//오른쪽 구간의 최솟값
		return Math.min(lMin, rMin);//두 구간 중 최소값 리턴
	}

	private static int init(int start, int end, int cur) {
		if (start == end) {
			tree[cur] = num[start];
			return tree[cur];
		}
		
		int mid = (start + end) / 2;
		int lMin = init(start, mid, 2 * cur);
		int rMin = init(mid + 1, end, 2 * cur + 1);
		return tree[cur] = Math.min(lMin, rMin); //두 구간의 최솟값을 저장
	}
}
