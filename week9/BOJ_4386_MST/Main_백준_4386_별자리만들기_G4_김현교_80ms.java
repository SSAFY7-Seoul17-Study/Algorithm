package MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 프림 알고리즘
 */

public class Main_백준_4386_별자리만들기_G4_김현교_80ms {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		double[][] mat = new double[N + 1][N + 1];
		Star[] stars = new Star[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double l = Double.parseDouble(st.nextToken());
			double r = Double.parseDouble(st.nextToken());
			stars[i] = new Star(l, r);
			int cur = i;
			for (int pre = i - 1; pre > 0; pre--) {
				double dist = getDist(cur, pre, stars);
				mat[cur][pre] = dist;
				mat[pre][cur] = dist;
			}
		}
	
		double[] minEdge = new double[N + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0.0;
		
		boolean[] visited = new boolean[N + 1];
		double result = 0.0;
		
		for (int i = 1; i <= N; i++) {
			double min = Integer.MAX_VALUE;
			int minVertex = 1;
			
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			
			visited[minVertex] = true;
			result += min;
			
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && minEdge[j] > mat[minVertex][j])
					minEdge[j] = mat[minVertex][j];
			}
		}
		
		System.out.printf("%.2f",result);
	}//end main
	
	private static double getDist(int cur, int pre, Star[] stars) {
		double diffY = stars[cur].l - stars[pre].l;
		double diffX = stars[cur].r - stars[pre].r;
		
		return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
	}

	static class Star {
		double l, r;

		public Star(double l, double r) {
			this.l = l;
			this.r = r;
		}
	}
}//end class

/*
 * 인접 행렬 -> 모든 점과 인접할 수 있으므로 인접 행렬이 가장 좋을 듯
 * 인접리스트 -> 모든 점과 인접할 수 있으므로 이것 역시 100!
 * 간선 리스트 -> 간선은 100!를 만들어야함
 * 
 * 
 * 
 * */
