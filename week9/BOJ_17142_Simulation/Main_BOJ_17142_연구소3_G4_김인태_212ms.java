import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17142_연구소3_김인태 {

	static class Virus{
		int x;
		int y;
		int time;
		
		public Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	static ArrayList<Virus> vList;
	static ArrayList<Integer> done;
	static int N, M, minTime, zero, zero2, doneSize;
	static int[][] map;
	static boolean[][] visited;
	static int[] nums;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 N  = Integer.parseInt(st.nextToken());
		 M  = Integer.parseInt(st.nextToken());
		 
		 map = new int[N][N];
		 vList = new ArrayList<Virus>();
		 
		 
		 for(int i=0; i<N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j=0; j<N; j++) {
				 int val = Integer.parseInt(st.nextToken());
				 if(val==2) {
					 vList.add(new Virus(i, j, 0));
				 }
				 if(val == 0) zero++;
				 map[i][j] = val;
			 }
		 }
		 
		 nums = new int[M];
		 visited = new boolean[N][N];
		 minTime = 3000;
		 if(zero==0) System.out.println(0);
		 else {
			 comb(0,0);
			 System.out.println(minTime == 3000 ? -1 : minTime);
		 }
		 
		 
	}	//	end of main
	
	public static void bfs() {
		init();
		zero2 = zero;
		Queue<Virus> que = new LinkedList<Virus>();
		for(int i=0; i<M; i++) {
			visited[vList.get(nums[i]).x][vList.get(nums[i]).y] = true;
			que.add(vList.get(nums[i]));
		}
		
		while(!que.isEmpty()) {
			Virus v = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny] || map[nx][ny]==1) continue;
				if(map[nx][ny]==0) zero2--;
				if(zero2 == 0) {
					minTime = Math.min(minTime, v.time+1);
					return;
				}
				
				visited[nx][ny] = true;
				que.add(new Virus(nx, ny, v.time+1));
			}
			
		}
	}
	
	public static void comb(int start, int cnt) {
		if(cnt==M) {
			bfs();
			return;
		}
		
		for(int i=start; i<vList.size(); i++) {
			nums[cnt] = i;
			comb(i+1, cnt+1);
		}
	}

	public static void init() {
		for(int i=0; i<N; i++) {
			Arrays.fill(visited[i], false);
		}
		
	}

}
