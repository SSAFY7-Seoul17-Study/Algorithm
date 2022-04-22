import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_백준_16235_나무재테크_골드4_이진오_1184ms
 * 
 *  - Map 클래스
 *    : 지도의 각 위치를 표현
 *    : 양분, 추가 양분, 해당하는 트리들의 리스트
 *  
 *  - Tree 클래스
 *    : 나무를 표현
 *    : 나무의 좌표, 나이
 *  
 *  - 구현
 *    : 봄, 여름, 가을, 겨울에 대한 메서드를 구현
 *      => 임시적으로 저장할 나무들에 대한 Queue 사용
 * 
 *  - 메모리
 *    : 300MB가량..
 */
public class Main_백준_16235_나무재테크_골드4_이진오_1184ms {

	private static int[] dr = { 1, 1, 1, 0, 0, -1, -1, -1 };
	private static int[] dc = { 1, 0, -1, 1, -1, 1, 0, -1 };

	static class Tree implements Comparable<Tree> {
		int r, c;
		int age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return age - o.age;
		}
	}

	static class Map {
		int food; // 양분
		int addi; // 추가 양분
		ArrayList<Tree> trees;

		public Map(int food, int addi) {
			this.food = food;
			this.addi = addi;
			this.trees = new ArrayList<Tree>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Map[][] map = new Map[N + 2][N + 2];

		for (int i = 1; i <= N; i++) { // 맵 초기화
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = new Map(5, a);
			}
		}

		// 나무가 존재하는 지역의 좌표
		ArrayList<int[]> coords = new ArrayList<>();
		// 임시 저장용 배열
		Queue<Tree> queue = new LinkedList<>();

		for (int i = 0; i < M; i++) { // 나무 정보 입력
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());

			Tree tree = new Tree(r, c, age);
			map[r][c].trees.add(tree);

			coords.add(new int[] { r, c });
		}
		
		for (int i = 0; i < K; i++) {
			// 봄, 여름, 가을, 겨울
			spring(map, coords, queue);
			summer(map, queue);
			autumn(map, coords, queue);
			winter(map);
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt += map[i][j].trees.size();
			}
		}
		
		System.out.println(cnt);
	}

	public static void spring(Map[][] map, ArrayList<int[]> coords, Queue<Tree> dead) {
		for (int i = coords.size() - 1; i >= 0; i--) {
			// 나무가 존재하는 좌표
			int[] coord = coords.get(i);
			int r = coord[0];
			int c = coord[1];

			// 내림차순 정렬
			ArrayList<Tree> trees = map[r][c].trees;
			Collections.sort(trees, Collections.reverseOrder());

			for (int j = trees.size() - 1; j >= 0; j--) {
				Tree tree = trees.get(j);

				if (tree.age <= map[r][c].food) {
					map[r][c].food -= tree.age;
					tree.age++;
				} else {
					dead.offer(tree);
					trees.remove(j);
				}
			}

			if (trees.isEmpty()) {
				coords.remove(i);
			}
		}
	}

	public static void summer(Map[][] map, Queue<Tree> dead) {
		while (!dead.isEmpty()) {

			Tree tree = dead.poll();

			map[tree.r][tree.c].food += tree.age / 2;
		}
	}

	public static void autumn(Map[][] map, ArrayList<int[]> coords, Queue<Tree> live) {
		for (int i = coords.size() - 1; i >= 0; i--) {
			// 나무가 존재하는 좌표
			int[] coord = coords.get(i);
			int r = coord[0];
			int c = coord[1];

			ArrayList<Tree> trees = map[r][c].trees;

			for (Tree tree : trees) {
				if (tree.age % 5 == 0) {
					for (int j = 0; j < dr.length; j++) {
						int nr = r + dr[j];
						int nc = c + dc[j];
						if (map[nr][nc] != null) { // 범위를 벗어나지 않았을 경우
							live.offer(new Tree(nr, nc, 1));
						}
					}
				}
			}
		}

		while (!live.isEmpty()) {
			Tree tree = live.poll();
			int r = tree.r;
			int c = tree.c;
			
			ArrayList<Tree> trees = map[r][c].trees;
			
			if (trees.isEmpty())
				coords.add(new int[] {r, c});

			trees.add(tree);
		}
	}

	public static void winter(Map[][] map) {
		int N = map.length - 2;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j].food += map[i][j].addi;
			}
		}
	}
}
