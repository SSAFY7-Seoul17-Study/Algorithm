import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_백준_2513_통학버스_골드3_이진오_448ms
 * 
 *  1. 아이디어
 *    1) 학교를 기준으로, 왼쪽에 존재하는 아파트 단지와 오른쪽에 존재하는 아파트 단지를 나누어 관리한다. 
 *    2) 학교를 기준으로, 멀리 있는 학생들부터 태워서 나른다. 
 *      => 아파트 단지를, 우선순위 큐를 통해 관리한다. 
 * 
 */
public class Main_백준_2513_통학버스_골드3_이진오_448ms {
	
	static class Apt {
		int x;
		int students;
		
		public Apt(int x, int students) {
			this.x = x;
			this.students = students;
		}
	}
	
	static class Bus extends Apt {
		public Bus(int x, int students) {
			super(x, students);
		}
		
		public void add(int students) {
			this.students += students;
		}
		
		public void clear() {
			this.students = 0;
		}
		
		public boolean isEmpty() {
			return this.students == 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 2 <= N <= 30,000	(아파트 단지의 수)
		int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 2,000	(통학버스의 정원)
		int S = Integer.parseInt(st.nextToken()); // 0 <= S <= 100,000	(학교의 위치)
		
		PriorityQueue<Apt> left  = new PriorityQueue<>((apt1, apt2) -> {
			return apt1.x - apt2.x;
		});
		PriorityQueue<Apt> right = new PriorityQueue<>((apt1, apt2) -> {
			return apt2.x - apt1.x;
		});
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int students = Integer.parseInt(st.nextToken());
			
			if (x < S) {
				left.offer(new Apt(x, students));
			}
			else {
				right.offer(new Apt(x, students));
			}
		}
		
		int dist = 0;
		Bus bus = new Bus(S, 0);
		
		while (!left.isEmpty()) {
			Apt apt = left.poll();
			
			if (bus.isEmpty())
				bus.x = apt.x;
			bus.add(apt.students);
			
			if (bus.students >= K) {
				// 첫 번째 K명의 학생들을 학교로 이동 = (S - bus.x) * 2;
				bus.students -= K;
				dist += (S - bus.x) << 1; 
				
				// 이후 학생들이 남아있다면, 현재 아파트 단지의 위치 x에서 이동
				if (bus.students > 0) {
					bus.x = apt.x;
					dist += (S - bus.x) * 2 * (bus.students / K);
					
					bus.students %= K;
				}
			}
		}
		
		if (bus.students > 0) {
			dist += (S - bus.x) * 2;
			bus.students = 0;
		}
		
		while (!right.isEmpty()) {
			Apt apt = right.poll();
			
			if (bus.isEmpty())
				bus.x = apt.x;
			bus.add(apt.students);
			
			if (bus.students >= K) {
				bus.students -= K;
				dist += (bus.x - S) << 1;
				
				if (bus.students > 0) {
					bus.x = apt.x;
					dist += (bus.x - S) * 2 * (bus.students / K);
					
					bus.students %= K;
				}
			}
		}
		
		if (bus.students > 0) {
			dist += (bus.x - S) * 2;
			bus.students = 0;
		}
		
		System.out.println(dist);
	}

}
