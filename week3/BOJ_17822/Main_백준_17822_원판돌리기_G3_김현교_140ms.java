package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_17822_원판돌리기_G3_김현교_140ms {
	
	static int N, M, T, sum, cnt;
	static int c[][], cmd[][];
	static boolean flag;
	static boolean[][] v;
	
	static int[] dl = {0, 1, 0, -1};//동남서북
	static int[] dr = {1, 0, -1, 0};
	static int many = 10000000;
	
	public static void main(String[] args) throws Exception {
		//1. 입력 받아서 1차원 배열에 알파벳 저장
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		c = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				c[i][j] = Integer.parseInt(st.nextToken());
				sum += c[i][j];
			}
		}
		cnt = N * M;
		
		int[] newArr = new int[M];
		v = new boolean[N + 1][M];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			//x를 M이하까지 배수로 while
			//1. 방향과 k값에 따른 시작점 찾기
			int start = getStart(d, k);
			int idx = x;
			while (idx <= N) {
				//원판 회전 시킴
				for (int j = 0; j < M; j++) {
					newArr[j] = c[idx][(start + j) % M];
				}
				//swap
				int[] tmp = c[idx];
				c[idx] = newArr;
				newArr = tmp;
				
				idx += x;
			}
			
			//그 원판들 완탐
			for (int l = 1; l <= N; l++) {
				for (int r = 0; r < M; r++) {
					if (!v[l][r])
						search(l, r, c[l][r], false);
				}
			}
			
			if (cnt == 0) {
				System.out.println(0);
				return;
			}
			if (!flag) {
				//평균값과 비교로 값들 계산하기 나머지가 0이면 같은값은 빼지 않기
				int avg = sum / cnt;
				boolean mod = sum % cnt != 0 ? true : false;
				for (int l = 1; l <= N; l++) {
					for (int r = 0; r < M; r++) {
						if (c[l][r] == many)
							continue;
						if (c[l][r] > avg) {
							c[l][r]--;
							sum--;
						} else if (c[l][r] < avg || mod) {
							c[l][r]++;
							sum++;
						} 
					}
				}
			}
			flag = false;
			for (int j = 1; j <= N; j++) {
				Arrays.fill(v[j], false);
			}
		}//end command
		
		System.out.println(sum);
	}//end main
	
	static int getStart(int d, int k) {
		switch (d) {
		case 0:
			return M - k;
		default:
			return k;
		}
	}
	
	static void search(int l, int r, int num, boolean off) {
		
		v[l][r] = true;
		if (c[l][r] == many)
			return;
		if (off) {
			c[l][r] = many;
			cnt--;
			sum -= num;
		}
		if (cnt == 0)
			return;
		//인접한 4개 숫자 확인
		for (int i = 0; i < 4; i++) {
			int nl = l + dl[i];
			int nr = r + dr[i];
			if (nr == -1)
				nr = M - 1;
			if (nr == M)
				nr = 0;
			if (nl == 0 || nl > N || v[nl][nr])
				continue;
			if (c[nl][nr] == num) {
				if (c[l][r] != many) {
					c[l][r] = many;
					sum -= num;
					cnt--;
				}
				if (!flag)
					flag = true;
				search(nl, nr, num, true);
			}
		}
	}
}//end class

/* 초기 생각
 * 	개수가 꽤 많음
 * 	어떤 로직인지 모르겠지만 완탐은 아닌듯
 * 	메모리가 많이주어지고 시간은 1초
 * 	다른 방법
 * 
 * 매 회전마다 판별식을 적용해서 로직구현이 되어야함.
 * 1. 배수에 해당하는 원판 회전
 * 2. 원판 위 숫자 확인, 인접숫자 확인 -> 같으면 지우기
 * 3. 같지않으면 평균 구해서 숫자 변경
 * 
 * ------- 첫 아이디어 -------
 * 	원판 회전 -> 숫자의 순서 바뀜 -> 어떻게 구현?
 * 		-> 어차피 숫자의 나열순서는 동일, 시작점만 바뀌는 것 -> 배열 + 시작점 index
 *  	-> 사라진 숫자는 0으로 변경
 *  	-> 인접 숫자 확인은 각 위치의 큐에 차례대로 넣고
 *  	-> 각 위치의 큐마다 숫자 꺼내서 pre값과 비교, 같지 않으면 넘어감, 같다면 해당 순서에 해당하는
 *  	-> 원판 2개의 해당 위치에 0을 넣음 
 * 
 * 위의 방법으로 최대 탐색 횟수는?
 * 회전 -> index숫자 계산
 * 인접숫자확인 -> 배열 한번 쭉 탐색(M)해서 큐에 넣기 = M * N -> 큐에서 빼기
 *  
 * ------- 다시 -------
 * 2차원 배열로 숫자를 저장하면 인접 확인이 매우 수월함(완탐 -> M x N x T = 125000번)
 * 회전시키는게 힘듬
 * 	i번째 원판 : arr[i]를 n번 시계방향으로 회전
 * 		=> arr[M - (n - 1)]번이 시작점이 됨
 * 		=> for i = 0 ~ M - 1
 * 		=> new arr[i] = arr[M - (n - 1) + i % M]
 * 		=> old arr = new arr;
 * 	
 *  M * 회전원판수 * 배열객체생성시간 * T = 50 * 25 * 1ms * 50 -> 62500microsec
 * 	회전하면서 평균도 구하기
 * 
 * 	2차원 배열의 저장공간은 인접 탐색시간을 얻고, 회전 시간을 포기한 자료구조임!
 * */
