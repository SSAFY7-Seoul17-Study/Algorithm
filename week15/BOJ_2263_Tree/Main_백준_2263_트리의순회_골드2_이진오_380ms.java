import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_2263_트리의순회_골드2_이진오_380ms
 * 
 */
public class Main_백준_2263_트리의순회_골드2_이진오_380ms {
	
	private static int[] indexes;
	private static int[] inOrder;
	private static int[] postOrder;
	
	private static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer inOrder_ = new StringTokenizer(br.readLine(), " ");
		StringTokenizer postOrder_ = new StringTokenizer(br.readLine(), " ");
		
		inOrder = new int[N];
		postOrder = new int[N];
		
		indexes = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(inOrder_.nextToken());
			postOrder[i] = Integer.parseInt(postOrder_.nextToken());
			
			indexes[inOrder[i]] = i;
		}
		
		build(N, 0, N, 0, N);
		
		System.out.println(sb.toString());
	}
	
	
	
	public static void build(int n, int i1, int i2, int p1, int p2) {
		if (n == 0)
			return;
		
		int c = postOrder[p2-1];
		
		int index = indexes[c];
		
		int l = index - i1;
		int r = n - l - 1;
		
		sb.append(c);
		if (l > 0) { // 왼쪽 서브트리
			sb.append(" ");
			build(l, i1, i1+l, p1, p1+l);
		}
		if (r > 0) { // 오른쪽 서브트리
			sb.append(" ");
			build(r, i2-r, i2, p1+l, p2-1);
		}
	}
}
