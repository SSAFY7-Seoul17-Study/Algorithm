import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_백준_17471_게리맨더링_골4_추준성 {
	private static boolean[] firstArea;
	private static int[] population;
	private static int N;
	private static int[][] adjMatrix;
	private static int result = Integer.MAX_VALUE;
	private static int sum;

	/*
	 * 0. 1선거구의 원소들을 결정지을 부분집합 완탐(subSet)을 돌림 
	 * 1. 선거구를 무조건 두 개로 나눌 수 있어야 함 (1구역 원소들끼리 전부 이어지고, 2구역 원소들끼리 전부 이어지면 됨)
	 * 2. 선거구를 두 개로 나눌 수 있다면 인구 차이의 최솟값과 비교 후 갱신 
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		firstArea = new boolean[N]; // 0~N-1번 노드의 방문처리용 배열
		population = new int[N]; // 0~N-1번 노드의 인구수 배열
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접 행렬 만들기 (0~N-1 노드)
		adjMatrix = new int[N][N];
		for (int from = 0; from < N; from++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			while(st.hasMoreTokens()) {
				int to = Integer.parseInt(st.nextToken())-1;
				adjMatrix[from][to] = 1;
			}
		}
		
		subSet(0);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	} // end of main
	
	public static void subSet(int depth) {
		if(depth == N) {
			int firstAreaValue = 0;
			int secondAreaValue = 0;
			
			for (int i = 0; i < N; i++) {
				if(firstArea[i]) { // 1구역에 할당된 경우
					firstAreaValue = i; // 1구역 대표값
				} else { // 2구역에 할당된 경우
					secondAreaValue = i; // 2구역 대표값
				}
			}
			// 1구역과 2구역이 전부 다 이어졌다면, 최솟값 갱신
			isComplete(firstAreaValue, secondAreaValue);
			return;
		}
		
		// 선택한 경우 (1구역에 할당됨)
		firstArea[depth] = true;
		subSet(depth+1);
		// 선택하지 않은 경우 (2구역에 할당됨)
		firstArea[depth] = false;
		subSet(depth+1);
		
	}
	
	public static void isComplete(int firstAreaValue, int secondAreaValue) {
		int firstAreaSum = population[firstAreaValue];
		int secondAreaSum = population[secondAreaValue];
		LinkedList<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		// 1구역 연결 확인
		queue.add(firstAreaValue);
		visited[firstAreaValue] = true;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for (int i = 0; i < N; i++) {
				// current와 연결되어 있고, 1구역에 속하고, 아직 방문하지 않았으면 방문처리
				if(adjMatrix[current][i] == 1 && firstArea[i] && !visited[i]) {
					firstAreaSum += population[i];
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		
		// 2구역 연결 확인
		queue.add(secondAreaValue);
		visited[secondAreaValue] = true;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for (int i = 0; i < N; i++) {
				// current와 연결되어 있고, 2구역에 속하고, 아직 방문하지 않았으면 방문처리
				if(adjMatrix[current][i] == 1 && !firstArea[i] && !visited[i]) {
					secondAreaSum += population[i];
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) return; // 하나라도 연결이 안 됐다면 그냥 리턴
		}
		
		// 모두 연결 됐다면 최솟값을 갱신
		sum = Math.abs(firstAreaSum - secondAreaSum);
		result = Math.min(result, sum);
		
	} // end of method isComplete
	
} // end of class
