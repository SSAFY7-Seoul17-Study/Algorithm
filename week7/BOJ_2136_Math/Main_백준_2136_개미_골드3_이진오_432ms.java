import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Main_백준_2136_개미_골드3_이진오_432ms
 * 
 *  - 개미의 위치에 따라서 정렬한다. 
 *    : a1, a2, ..., aN
 *  
 *  - 왼쪽으로 이동하는 개미, 오른쪽으로 이동하는 개미의 수를 헤아린다. 
 *    : 앞에서부터 l마리의 개미는, 막대의 첫 부분으로 떨어진다.  
 *    : 뒤에서부터 r마리의 개미는, 막대의 끝 부분으로 떨어진다.
 *    
 *  - 앞에서부터 l번째 개미
 *    : 왼쪽으로 이동하는 개미 중, 맨 끝 개미의 이동 거리만큼 이동한다. (0으로 떨어지는 개미 중 최대 거리)
 *  
 *  - 뒤에서부터 r번째 개미
 *    : 오른쪽으로 이동하는 개미 중, 맨 첫 개미의 이동 거리만큼 이동한다. (L로 떨어지는 개미 중 최대 거리)
 *  
 *    => 두 개미의 이동 거리 중, 더 긴 거리가 최대 이동 시간이 된다. 
 *    => 이 때, 해당 개미의 인덱스는 가장 마지막에 떨어지는 개미의 인덱스가 된다. 
 * 
 */
public class Main_백준_2136_개미_골드3_이진오_432ms {
	
	static class Ant {
		int x;
		int index;
		public Ant(int x, int index) {
			this.x = x;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // N <= 10^5
		int L = Integer.parseInt(st.nextToken()); // L <= 10^9
				
		int l = 0; // 왼쪽으로 이동하는 개미의 수
		
		int lDist = 0; // 왼쪽으로 이동하는 개미 중, 최장 이동 거리
		int rDist = 0; // 오른쪽으로 이동하는 개미 중, 최장 이동 거리
		
		Ant[] ants = new Ant[N];
		
		for (int i = 0; i < ants.length; i++) {
			int x = Integer.parseInt(in.readLine()); // x <= L
			if (x < 0) {
				ants[i] = new Ant(-x, i+1);
				l++;
				if (-x > lDist)
					lDist = -x;
			} else {
				ants[i] = new Ant(x, i+1);
				if (L-x > rDist)
					rDist = L - x;
			}
		}
		
		Arrays.sort(ants, new Comparator<Ant>() {
			@Override
			public int compare(Ant o1, Ant o2) {
				return o1.x - o2.x;
			}
		});
		
		int dist = 0;
		int index = 0;
		
		if (lDist >= rDist) {
			dist = lDist;
			index = ants[l-1].index;
		} else {
			dist = rDist;
			index = ants[l].index;
		}
		
		System.out.println(index+" "+dist);
	}
}
