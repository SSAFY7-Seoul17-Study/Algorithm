import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_����_2513_���й���_���3_������_448ms
 * 
 *  1. ���̵��
 *    1) �б��� ��������, ���ʿ� �����ϴ� ����Ʈ ������ �����ʿ� �����ϴ� ����Ʈ ������ ������ �����Ѵ�. 
 *    2) �б��� ��������, �ָ� �ִ� �л������ �¿��� ������. 
 *      => ����Ʈ ������, �켱���� ť�� ���� �����Ѵ�. 
 * 
 */
public class Main_����_2513_���й���_���3_������_448ms {
	
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
		
		int N = Integer.parseInt(st.nextToken()); // 2 <= N <= 30,000	(����Ʈ ������ ��)
		int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 2,000	(���й����� ����)
		int S = Integer.parseInt(st.nextToken()); // 0 <= S <= 100,000	(�б��� ��ġ)
		
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
				// ù ��° K���� �л����� �б��� �̵� = (S - bus.x) * 2;
				bus.students -= K;
				dist += (S - bus.x) << 1; 
				
				// ���� �л����� �����ִٸ�, ���� ����Ʈ ������ ��ġ x���� �̵�
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
