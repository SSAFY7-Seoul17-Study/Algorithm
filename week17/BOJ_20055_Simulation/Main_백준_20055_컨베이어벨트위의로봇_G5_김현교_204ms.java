package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_20055_컨베이어벨트위의로봇_G5_김현교_204ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int end = 2 * N;
		Belt[] belts = new Belt[end + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= end; i++) {
			belts[i] = new Belt(Integer.parseInt(st.nextToken()), false);
		}
		
		Conveyor conveyor = new Conveyor(N, belts);
		
		int cnt = 1;
		while (true) {
			conveyor.rotateBelts();
			conveyor.moveRobot();
			conveyor.putNewRobot();
			if (conveyor.getZeroCnt() >= K)
				break;
			cnt++;
		}
		
		System.out.println(cnt);
	}//end main
	
	static class Conveyor {
		int N, end, zeroCnt;
		Belt[] belts;
		
		public Conveyor(int N, Belt[] belts) {
			this.N = N;
			this.belts = belts;
			this.end = 2 * N;
		}
		
		public void rotateBelts() {
			belts[0] = belts[end];
			for (int i = end - 1; i >= 0; i--) {
				belts[i + 1] = belts[i];
				if (i + 1 >= N)
					belts[i + 1].hasRobot = false;
			}
		}
		
		public void moveRobot() {
			for (int cur = N - 1; cur > 0; cur--) {
				if (!belts[cur].hasRobot)
					continue;
				
				int next = cur + 1;
				if (belts[next].durability > 0 && !belts[next].hasRobot) {
					if (--belts[next].durability == 0)
						zeroCnt++;
					belts[next].hasRobot = true;
					belts[cur].hasRobot = false;
				}
			}
		}
		
		public void putNewRobot() {
			if (belts[1].durability > 0 && !belts[1].hasRobot) {
				if (--belts[1].durability == 0)
					zeroCnt++;
				belts[1].hasRobot = true;
			}
		}
		
		public int getZeroCnt() {
			return zeroCnt;
		}
	}
	
	static class Belt {
		int durability;
		boolean hasRobot;
		
		public Belt(int durability, boolean hasRobot) {
			super();
			this.durability = durability;
			this.hasRobot = hasRobot;
		}
	}
}//end class
/*
 * 1. 올리는칸(1번)의 내구도만큼 로봇이 올라감
 * 2. 매 턴마다 한칸씩 오른쪽 이동
 * 3. 로봇들이 한칸씩 이동할 수 있으면 이동
 * 4. 내구도가 0인 칸의 개수가 K개가 되면 과정 종료
 * 
 * 로봇 순서는 N번부터 세면 됨
 * 
 * 컨베이어 벨트 {
 *   벨트 배열
 *   로봇 배열
 *   
 * }
 * 
 * */
