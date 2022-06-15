import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Main_백준_1941_소문난칠공주_골드3_184ms_BFS
 * 
 *  - 자료 구조
 *    1) char[] map: 5x5 배열을, 1차원으로 나열해서 사용
 *    2) int[] memo: 25비트의 방문 정보를 저장
 *  
 *  - BFS (140MB, 184ms)
 *    1) 현재 다솜파 정보(bit)와 인접한 자리를 추가하며 BFS를 진행한다. 
 *    2) 다솜파 인원이 4명이 될 수 없는 경우 추가하지 않는다.
 *    3) 현재 다솜파의 자리와 붙어있지 않는 자리의 경우 추가하지 않는다. 
 *    
 *  - 단점
 *    1) 모든 방문 정보를 비트로 변환하여, 배열에 저장하므로 메모리 낭비가 심하다.
 *    2) 비트마스킹 - 해석이 어렵다.
 */
public class Main_백준_1941_소문난칠공주_골드3_이진오_184ms_BFS {
	
	static int cnt;
	static int[] memo = new int[1<<25];
	static char[] map = new char[25];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i*5 + j] = line.charAt(j);
			}
		}
		
		Arrays.fill(memo, -1);
		
		Queue<int[]> queue = new LinkedList<int[]>();
		
		for (int i = 0; i < map.length; i++) {
			int bit = 1 << i;
			int dasom = map[i] == 'S' ? 1 : 0;
			
			queue.offer(new int[] {bit, dasom});
			memo[bit] = dasom;
		}
		
		int level = 1;
		while (!queue.isEmpty() && level < 7) {
			int size = queue.size();
			
			while (size-- > 0) {
				int[] state = queue.poll();
				
				int bit = state[0];
				int dasom = state[1];
				
				for (int i = 0; i < map.length; i++) {
					if ((bit & 1 << i) > 0 || memo[bit | 1 << i] >= 0 || !adjacent(bit, i))
						continue;
					
					if (map[i] == 'S') {
						if (3 + dasom >= level) {
							memo[bit | 1 << i] = dasom + 1;
							queue.offer(new int[] {bit | 1 << i, dasom + 1});
							if (level == 6)
								cnt++;
						}
					}
					else {
						if (2 + dasom >= level) {
							memo[bit | 1 << i] = dasom;
							queue.offer(new int[] {bit | 1 << i, dasom});
							if (level == 6)
								cnt++;
						}
					}
				}
			}
			
			level++;
		}
		
		System.out.println(cnt);
	}

	public static boolean adjacent(int bit, int i) {
		for (int b = 0; b < 25; b++) {
			if ((bit & 1 << b) > 0) {
				if (b / 5 == i / 5 && Math.abs(b - i) == 1 || 
						b % 5 == i % 5 && Math.abs(b - i) == 5) {
					return true;
				}
			}
		}
		
		return false;
	}
}
