import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Main_백준_치킨배달_골드5_이진오_104ms
 * 
 * 2022-06-10
 * 
 */
public class Main_백준_15686_치킨배달_골드5_이진오_104ms {
	
	private static int N;
	private static int M;
	
	private static int H;
	private static int C;
	
	private static ArrayList<int[]> houses = new ArrayList<>();
	private static ArrayList<int[]> chickens = new ArrayList<>();
	
	private static int[] selected;
	private static int[][] dist;
	
	private static int min = 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < N; j++) {
				switch (line.charAt(j<<1)) {
				case '1':
					houses.add(new int[] {i, j});
					break;
				case '2':
					chickens.add(new int[] {i, j});
					break;
				}
			}
		}
		
		H = houses.size();
		C = chickens.size();
		
		dist = new int[H][C];
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < C; j++) {
				dist[i][j] = getHamilton(houses.get(i), chickens.get(j));
			}
		}
		
		selected = new int[M];
		
		combi(0, 0);
		
		System.out.println(min);
	}
	
	public static void combi(int idx, int start) {
		if (idx == M) {
			int d = calc();
			if (d < min)
				min = d;
			return;
		}
		
		for (int i = start; i < C; i++) {
			selected[idx] = i;
			combi(idx+1, i+1);
		}
	}
	
	public static int calc() {
		int ans = 0;
		
		for (int h = 0; h < H; h++) {
			int d = 100;
			int[] dist_ = dist[h];
			for (int c : selected)
				if (dist_[c] < d)
					d = dist_[c];
			
			ans += d;
		}
		
		return ans;
	}

	public static int getHamilton(int[] coord1, int[] coord2) {
		return Math.abs(coord1[0] - coord2[0]) + Math.abs(coord1[1] - coord2[1]);
	}
}
