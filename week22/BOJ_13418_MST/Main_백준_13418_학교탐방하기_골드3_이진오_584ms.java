import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_����_13418_�б�Ž���ϱ�_���3_������_584ms
 * 
 *  1) ������ ���
 *    : ��� �������濡 ���Ͽ�, union ������ �����Ѵ�. 
 *      - ������� ���� ������ ������ ��Ƹ���. 
 *      - �ش� ������, ��������� ����Ǿ�߸� �ϴ� ���յ��� �ȴ�. 
 *        => �̸� ���ؼ�, �Ƿε� k�� ����� �� �ִ�.  
 *  
 *  2) �־��� ���
 *    : ��� �������濡 ���Ͽ�, union ������ �����Ѵ�. 
 *      - ������� ���� ������ ������ ��Ƹ���. 
 *      - �ش� ������, ��������� ����Ǿ�߸� �ϴ� ���յ��� �ȴ�.
 *        => �̸� ���ؼ�, �Ƿε� k�� ����� �� �ִ�.  
 * 
 */
public class Main_����_13418_�б�Ž���ϱ�_���3_������_584ms {
	
	private static int[] k;
	private static int[] p;
	
	private static List<int[]> asc;
	private static List<int[]> desc;
	
	private static int N;
	private static int M;

	public static int find(int x) {
		if (p[x] == x)
			return x;
		return p[x] = find(p[x]);
	}
	
	public static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX == rootY)
			return false;
		p[rootY] = rootX;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // �ǹ��� ��, 1 <= N <= 1,000
		M = Integer.parseInt(st.nextToken()); // ������ ��, 1 <= M <= N(N-1)/2
		
		k = new int[2];
		p = new int[N+1];
		
		// �Ա��� 1�� �ǹ����� ���� ����, offset
		String line = br.readLine();
		int offset = line.charAt(line.length() - 1) - '0';
		
		Arrays.fill(k, offset == 0 ? 1 : 0);
		
		// �������� �� �������濡 ���� ����
		asc = new ArrayList<int[]>();
		desc = new ArrayList<int[]>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int v0 = Integer.parseInt(st.nextToken());
			int v1 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if (weight == 1)
				desc.add(new int[] {v0, v1});
			else
				asc.add(new int[] {v0, v1});
		}
		
		// p �迭 �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
		// default: �Ա��� 1�� �ǹ� union
		union(0, 1);
		int cnt = N;
		
		for (int[] edge : desc) {
			if (union(edge[0], edge[1])) {
				cnt--;
			}
		}
		k[0] += (cnt - 1);
		
		// p �迭 �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
		union(0, 1);
		cnt = N;
		
		for (int[] edge : asc) {
			if (union(edge[0], edge[1]))
				cnt--;
		}
		k[1] += (N - cnt);
		
		System.out.println(k[1]*k[1] - k[0]*k[0]);
	}
	
}
