import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 상어의 움직임에서 애먹음
 * @author kit938639
 *
 */
public class Main_BOJ_17143_낚시왕_G2_608ms {
	
	static class Shark{
		int r;
		int c;
		int s;
		int d;
		int z;
		
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "("+s+","+d+","+z+")";
		}
		
		
	
	}
	
	static int R, C, M, totalSize;
	static Shark[][] map, copy;
	private static int[] dr = {0,-1,1,0,0};
	private static int[] dc = {0,0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		init();
		process();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R+1][C+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Shark(r,c,s,d,z);  
		}
	}
	
	private static void process() {
		for(int i=1; i<=C; i++) {
//			System.out.println("===========================================");
//			System.out.println("i: "+i);
//			printMap();
//			System.out.println("-------------------------------------------");
			fishing(i);
//			printMap();
//			System.out.println("-------------------------------------------");
			sharkAct();
		}
		System.out.println(totalSize);
	}
	
	private static void fishing(int c) {
		for(int r=1; r<=R; r++) {
			if(map[r][c] != null) {
				totalSize += map[r][c].z;
				map[r][c] = null;
				return;
			}
		}
	}
	
	private static void sharkAct() {
		Queue<Shark> que = new LinkedList<Shark>();
		for(int i=1; i<=R ;i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]!=null) que.add(map[i][j]);
			}
		}
		map = new Shark[R+1][C+1];
		
		while(!que.isEmpty()) {
			Shark shark = que.poll();
			moveAndEat(shark);
		}
	}
	
	public static void moveAndEat(Shark shark) {
		Shark movedShark = move(shark);
		eat(movedShark);
	}
	
	public static Shark move(Shark shark) {
		int r = shark.r;
		int c = shark.c;
		int s = shark.s;
		int d = shark.d;
		int z = shark.z;
		
		int m = 0;
		
		if(d==1 || d==2) {
			m = s % ((R-1) * 2);
		}
		if(d==3 || d==4) {
			m = s % ((C-1) * 2);
		}
		
		for(int i=0; i<m; i++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<1 || nr>R || nc<1 || nc>C) {
				r -= dr[d];
				c -= dc[d];
				switch (d) {
				case 1:
					d=2;
					break;
				case 2:
					d=1;
					break;
				case 3:
					d=4;
					break;
				case 4:
					d=3;
					break;
				}
			}else {
				r = nr; c = nc;				
			}
		}	//	end of for
		
		return new Shark(r,c,s,d,z);
	}	//	 end of move
	
	public static void eat(Shark shark) {
		int r = shark.r;
		int c = shark.c;
		
		if(map[r][c] != null) {
			if(map[r][c].z < shark.z) {
				map[r][c] = shark;
			}
		}else {
			map[r][c] = shark;
		}
	}	// end of eat
	
	public static void printMap() {
		for(int i=0; i<=R; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
}	//	end of class
