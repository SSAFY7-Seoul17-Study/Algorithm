package study.day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_백준_4195_친구네트워크_골2_추준성_556ms {
	/*
	 * <설계>
	 * 1. 네트워크 연결을 지속적으로 갱신하여 집합 생성 => union-find 알고리즘 
	 * 2. 이전 문제들처럼 index가 숫자가 아니라 문자열로 주어짐 => 문자열을 key값으로 보고 인덱스화 시켜야함 => 해싱기법 활용
	 * 3. rank[] 배열을 만들어서 해당 루트를 포함한 집합이 몇 개의 노드를 포함하고 있는지 누적해서 저장
	 * 
	 * <아이디어>
	 * 1. union-find 알고리즘
	 * 2. HashMap 활용한 해싱기법 (문자열 -> 숫자 인덱스)
	 * 
	 */
	static void makeSet(int N) {
		for (int i = 0; i < N; i++) {
			parent[i] = i; // 자기 자신을 부모로 초기 설정
			rank[i] = 1; // 각 노드들에 자기 자신 외에 연결된 건 아무것도 없으므로 노드 개수 1로 초기화
		}
	}
	
	static int findRoot(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findRoot(parent[a]);
	}
	
	static int unionSet(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		
		if(aRoot == bRoot) return rank[aRoot]; // 루트가 동일하면 그 루트의 rank 값 반환
		
		parent[bRoot] = aRoot; // 항상 왼쪽 원소 루트가 오른쪽 원소 루트의 루트가 되게끔 지정
		rank[aRoot] += rank[bRoot]; // 왼쪽 원소 루트 rank값을 갱신 (집합이 합쳐졌으므로 오른쪽 원소 루트가 가진 rank값을 더해줌)
		
		return rank[aRoot]; // 왼쪽 원소의 루트를 포함한 해당 집합의 원소의 개수 반환
	}
	
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			
			int F = Integer.parseInt(br.readLine()); // 친구 관계의 수
			
			parent = new int[2 * F];
			rank = new int[2 * F];
			
			makeSet(2 * F); // 친구의 최대 수는 2 * F
			
			Map<String, Integer> map = new HashMap<>(); // 문자열(Key)을 인덱스(Value)화 할 HashMap 생성 
			
			int idx = 0;

			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String from = st.nextToken();
				String to = st.nextToken();
				
				// from에 해당하는 사람이 없으면 해싱처리
				if(!map.containsKey(from)) {
					map.put(from, idx++);
				}
				
				// to에 해당하는 사람이 없으면 해싱처리
				if(!map.containsKey(to)) {
					map.put(to, idx++);
				}
				
				// from, to에 해당하는 value(인덱스) 값을 활용하여 union-find 알고리즘 진행
				sb.append(unionSet(map.get(from), map.get(to))).append("\n"); // 항상 왼쪽 원소 루트가 오른쪽 원소 루트의 루트가 되게끔 지정
			}
			
		} // end of for testCase
		System.out.print(sb.toString());
	} // end of main

} // end of class
