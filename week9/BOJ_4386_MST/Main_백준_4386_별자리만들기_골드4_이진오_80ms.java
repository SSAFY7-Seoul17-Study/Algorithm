import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_백준_4386_별자리만들기_골드4_이진오_80ms
 * 
 *  - MST(PRIM 알고리즘)
 *    : 현재까지 선택된 별의 집합으로부터, 가장 가까운 별을 선택하여 집합에 추가
 *   
 */
public class Main_백준_4386_별자리만들기_골드4_이진오_80ms {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		float[][] coords = new float[N][2];
		float[] dist = new float[N];
		boolean[] selected = new boolean[N];
		
		for (int i = 0; i < coords.length; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			coords[i][0] = Float.parseFloat(st.nextToken());
			coords[i][1] = Float.parseFloat(st.nextToken());
		}
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// 첫 번째 별 선택
		int star = 0;
		int next = 0;
		int cnt = 1;
		float totalDist = 0;
		dist[star] = 0.0F;
		selected[star] = true;
		
		while (cnt < N) {
			float x = coords[star][0];
			float y = coords[star][1];
			
			float min = 1001;
			
			for (int i = 0; i < coords.length; i++) {
				if (!selected[i]) {
					// 현재 별과 i번째 별의 거리
					float d = (float) Math.sqrt((x - coords[i][0]) * (x - coords[i][0]) + 
							(y - coords[i][1]) * (y - coords[i][1]));
					
					// i번째 별과의 거리가 가깝다면 업데이트
					if (d < dist[i]) {
						dist[i] = d;
					}
					// 그렇지 않다면, d를 다시 dist[i]로 업데이트
					else {
						d = dist[i];
					}
					// 다음 별을 선택
					if (d < min) {
						min = d;
						next = i;
					}
				}
			}
			
			totalDist += min;
			star = next;
			selected[star] = true;
			cnt++;
		}
		
		System.out.printf("%.2f\n", totalDist);
		
	} // end of main
	
} // end of class
