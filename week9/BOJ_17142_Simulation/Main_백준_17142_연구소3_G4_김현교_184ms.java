package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 객체 : virus, 연구소, 나쁜놈(승원)
 * 
 * 바이러스 : 바이러스의 위치를 보관
 * 
 * 연구소 : 연구소의 상태를 보관
 * 
 * 나쁜놈
 * 	- 바이러스 리스트를 가지고 있음
 * 	- 해당 바이러스 리스트 중 활성화할 바이러스를 선택함
 * 	- 선택한 바이러스를 연구소에 활성화시킴 -> BFS
 *  - 확산 속도가 가장 빠른 바이러스를 확인하고 반환
 * 
 * */

public class Main_백준_17142_연구소3_G4_김현교_184ms {
	
	public static void main(String[] args) throws Exception {
		Laboratory laboratory = new Laboratory();
		Villain seungWon = new Villain();
		
		init(laboratory, seungWon);
		
		System.out.println(seungWon.startWorking(laboratory));
	}//end main

	private static void init(Laboratory laboratory, Villain villain) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int labSize = Integer.parseInt(st.nextToken());
		int activeCnt = Integer.parseInt(st.nextToken());
		
		int[][] lab = new int[labSize][labSize];
		int safeZoneCnt = 0;
		ArrayList<Virus> virusList = new ArrayList<>(10);
		
		for (int i = 0; i < labSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < labSize; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				
				if (lab[i][j] == 2) virusList.add(new Virus(i, j));
				else if (lab[i][j] == 0) safeZoneCnt++;
			}
		}
		
		villain.setVirusList(virusList);
		villain.setVirusCnt(virusList.size());
		villain.setActiveCnt(activeCnt);
		
		laboratory.setLab(lab);
		laboratory.setLabSize(labSize);
		laboratory.setSafeZoneCnt(safeZoneCnt);
	}
	
	static class Virus {
		int l, r;

		public Virus(int l, int r) {
			this.l = l;
			this.r = r;
		}
	} //end Virus class
	
	static class Laboratory {
		private int labSize;
		private int[][] lab;
		private int safeZoneCnt;
		
		public void setLabSize(int labSize) {
			this.labSize = labSize;
		}

		public void setLab(int[][] lab) {
			this.lab = lab;
		}

		public void setSafeZoneCnt(int safeZoneCnt) {
			this.safeZoneCnt = safeZoneCnt;
		}

		public int getSafeZoneCnt() {
			return safeZoneCnt;
		}

		public int[][] getLab() {
			return lab;
		}

		public int getLabSize() {
			return labSize;
		}
	} //end Laboratory Class
	
	static class Villain {
		
		private ArrayList<Virus> virusList;
		private int virusCnt;
		private int activeCnt;
		private int minTime;
		
		private Queue<Virus> q;
		private Virus[] activeVirusList;
		private boolean[][] visited;
		
		private static final int[] dl = {1, 0, -1, 0};
		private static final int[] dr = {0, 1, 0, -1};
		private static final int MAX = Integer.MAX_VALUE;

		public int startWorking(Laboratory laboratory) {
			q = new LinkedList<Virus>();
			activeVirusList = new Virus[10];
			visited = new boolean[laboratory.labSize][laboratory.labSize];
			minTime = MAX;
			
			chooseAndActivateVirus(0, 0, laboratory);
			return minTime == MAX ? -1 : minTime;
		}
		
		private void chooseAndActivateVirus(int start, int depth, Laboratory laboratory) {
			
			if (depth == activeCnt) {
				activateVirus(laboratory);
				clearAll(laboratory.labSize);
				return;
			}
			
			for (int i = start; i < virusCnt; i++) {
				Virus choice = virusList.get(i);
				visited[choice.l][choice.r] = true;
				activeVirusList[depth] = choice;
				
				chooseAndActivateVirus(i + 1, depth + 1, laboratory);
			}
		}

		private void activateVirus(Laboratory laboratory) {
			int safeCnt = laboratory.getSafeZoneCnt();
			int[][] lab = laboratory.getLab();
			int labSize = laboratory.getLabSize();
			for (int i = 0; i < activeCnt; i++) {
				q.offer(activeVirusList[i]);
			}
		
			int time = 0;
			while (!q.isEmpty() && safeCnt > 0) {
				safeCnt = spreadVirus(safeCnt, lab, labSize, q.size());
				if (++time > minTime) break;
			}
			
			if (safeCnt == 0 && time < minTime)
				minTime = time;
		}

		private int spreadVirus(int safeCnt, int[][] lab, int labSize, int qSize) {
			while (qSize-- > 0) {
				Virus cur = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nl = cur.l + dl[i];
					int nr = cur.r + dr[i];
					if (isNotValid(nl, nr, labSize, lab))
						continue;
					
					if (lab[nl][nr] != 2) safeCnt--;
					visited[nl][nr] = true;
					q.offer(new Virus(nl, nr));
				}
			}
			return safeCnt;
		}

		private boolean isNotValid(int nl, int nr, int labSize, int[][] lab) {
			return nl < 0 || nl >= labSize || nr < 0 || nr >= labSize || visited[nl][nr] || lab[nl][nr] == 1;
		}

		private void clearAll(int labSize) {
			q.clear();
			for (int i = 0; i < labSize; i++)
				Arrays.fill(visited[i], false);
		}

		public void setVirusList(ArrayList<Virus> virusList) {
			this.virusList = virusList;
		}

		public void setVirusCnt(int virusCnt) {
			this.virusCnt = virusCnt;
		}

		public void setActiveCnt(int activeCnt) {
			this.activeCnt = activeCnt;
		}
	}//end Villain Class
	
}//end Main class
