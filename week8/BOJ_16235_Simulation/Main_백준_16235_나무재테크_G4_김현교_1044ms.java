package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_16235_나무재테크_G4_김현교_1044ms {
	
	static int N, treeCnt, years;
	static int[][] add, land;
	static PriorityQueue<Tree> pq;
	private static boolean[][] lack;
	
	static int[] dl = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dr = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static ArrayList<Tree> live, death;
	
	public static void main(String[] args) throws Exception {
		input();
		init();
		while (years-- > 0) {
			spring(live, death);
			summer(death);
			fall(live);
			winter(add);
			
			clearAll(live, death);
		}
		
		System.out.println(treeCnt);
	}//end main

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		treeCnt = Integer.parseInt(st.nextToken());
		years = Integer.parseInt(st.nextToken());
		
		add = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pq = new PriorityQueue<Tree>();
		for (int i = 0; i < treeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			pq.offer(new Tree(l, r, age));
		}
	}

	private static void init() throws IOException {
		land = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++)
			Arrays.fill(land[i], 5);
		
		live = new ArrayList<Tree>();
		death = new ArrayList<Tree>();
		lack = new boolean[N + 1][N + 1];
	}
	
	private static void clearAll(ArrayList<Tree> live, ArrayList<Tree> death) {
		for (int i = 1; i <= N; i++)
			Arrays.fill(lack[i], false);
		live.clear();
		death.clear();
	}

	private static void spring(ArrayList<Tree> live, ArrayList<Tree> death) {
		while (!pq.isEmpty()) {
			Tree cur = pq.poll();
			
			int l = cur.l;
			int r = cur.r;
			if (lack[l][r] || land[l][r] < cur.age) {
				if (!lack[l][r]) lack[l][r] = true;
				death.add(cur);
				continue;
			}
			
			land[l][r] -= cur.age;
			cur.age++;
			live.add(cur);
		}
	}

	private static void summer(ArrayList<Tree> death) {
		for (Tree tree : death) {
			int l = tree.l;
			int r = tree.r;
			land[l][r] += tree.age / 2;
			
			treeCnt--;
		}
	}

	private static void fall(ArrayList<Tree> live) {
		for (Tree cur : live) {
			if (cur.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nl = cur.l + dl[i];
					int nr = cur.r + dr[i];
					if (nl < 1 || nl > N || nr < 1 || nr > N)
						continue;
					pq.offer(new Tree(nl, nr, 1));
					treeCnt++;
				}
			}
			pq.offer(cur);
		}
	}
	

	private static void winter(int[][] A) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				land[i][j] += A[i][j];
			}
		}
	}
	
	static class Tree implements Comparable<Tree>{
		int l, r, age;

		public Tree(int l, int r, int age) {
			super();
			this.l = l;
			this.r = r;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return age - o.age;
		}
	}
}//end class
/*
 * 봄구현 다시
 * pq에 그냥 위치구분없이 다 넣고
 * 해당 위치에 양분 부족 flag를 세워서
 * false면 live, true면 death큐에 넣어주기
 * 
 * 1. 봄 구현
 * - 각 위치를 1차원 숫자로
 * - PQ배열에 각 위치에 해당하는 나무를 넣어줌(age로 정렬, 나무객체)
 * - 나무 위치를 check배열로 체킹
 * - 봄 로직 실행
 * 	- PQ의 size로 체크 or check배열로 체크해서 비어있지 않으면 로직 실행
 * 	- PQ에서 하나씩 꺼내서 그 나무 나이와 map의 양분 비교
 * 		- map이 크면 빼주고 나이 추가한 뒤 live queue에 넣어줌.
 * 		- map이 작으면 PQ의 모든 나무를 death queue에 넣어줌
 * 		(clear한 death list에 위치값을 추가해주면 좋을듯?)
 *  - PQ size가 0이되면 종료 or live queue나무들을 다시 채워줌?(가을 고려)
 *  
 * 2. 여름 구현
 * 	- death queue가 비어있지 않은 부분을 찾음.
 * 	- death queue에서 나무 하나씩 꺼내서 나이 /2만큼 map에 채워줌
 * 
 * 3. 가을
 * 	- live queue의 나무들의 나이를 확인
 * 	- 5배수가 아니면 PQ에 넣어주기
 * 	- 5배수이면 8방으로 번식 후 PQ에 넣어주기
 * 4. 겨울
 *  - 2중 반복문으로 양분채워주기
 * 
 * */
