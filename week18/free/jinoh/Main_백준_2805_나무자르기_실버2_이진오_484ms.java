import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_2805_나무자르기_실버2_이진오_484ms
 * 
 *  - 매개변수 탐색(Parametric Search)
 *    : 
 * 
 */
public class Main_백준_2805_나무자르기_실버2_이진오_484ms {
	
	private static int max;
	private static int[] trees;
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			int tree = Integer.parseInt(st.nextToken());
			trees[i] = tree;
			if (tree > max)
				max = tree;
		}
		
		System.out.println(paramSearch());
	}
	
	public static int paramSearch() {
		int lo = 0;
		int hi = max - 1;
		
		while (lo < hi) {
			int mid = (lo + hi) >> 1;
			if (getLen(mid) < M) {
				hi = mid - 1;
			}
			else {
				if (getLen(mid+1) < M)
					return mid;
				else
					lo = mid + 1;
			}
		}
		
		return lo;
	}
	
	public static long getLen(int h) {
		long len = 0;
		for (int tree : trees)
			if (tree > h) {
				len += tree - h;
			}
		
		return len;
	}
}
