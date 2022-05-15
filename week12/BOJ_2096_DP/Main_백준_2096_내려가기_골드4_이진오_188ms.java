import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main_백준_2096_내려가기_골드4_이진오_188ms
 * 
 *  - dp 상향식
 *  
 *    1) max
 *      : maxBlock[i][0] = max(maxBlock[i+1][0], maxBlock[i+1][1]) + block[i][0];
 *      : maxBlock[i][1] = max(maxBlock[i+1][0], maxBlock[i+1][1], maxBlock[i+1][2]) + block[i][1];
 *      : maxBlock[i][2] = max(maxBlock[i+1][1], maxBlock[i+1][2]) + block[i][2];
 *    
 *    2) min은 반대로
 *      : ...
 *    
 *    3) 최적화(1)
 *      : maxBlock[i+1] => max0, max1, max2에 저장
 *      : minBlock[i+1] => min0, min1, min2에 저장
 *    
 *    4) 최적화(2)
 *      : 연산 순서 조정
 *      
 *      ex) max0 < max1 < max2인 경우
 *        => max0 = max1 + block[i][0];
 *        => max1 = max2 + block[i][1];
 *        => max2 = max2 + block[i][2];
 *        
 *        위와 같은 순서로 해야 정확한 값을 구할 수 있다. (대소가 다른 경우에는, 순서를 조정하여야 한다.)
 * 
 */
public class Main_백준_2096_내려가기_골드4_이진오_188ms {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); // 줄의 개수, N <= 100,000
		
		int[][] lines = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < 3; j++) {
				lines[i][j] = line.charAt(j<<1) - '0';
			}
		}
		
		int max0 = lines[N-1][0];
		int min0 = max0;
		int max1 = lines[N-1][1];
		int min1 = max1;
		int max2 = lines[N-1][2];
		int min2 = max2;
		
		for (int i = N-2; i >= 0; i--) {
			if (max0 > max1) {
				if (max1 > max2) { // max0 > max1 > max2
					max2 = max1 + lines[i][2];
					max1 = max0 + lines[i][1];
				}
				else {
					if (max0 > max2) { // max0 > max2 >= max1
						max1 = max0 + lines[i][1];
					}
					else { // max2 >= max0 > max1
						max1 = max2 + lines[i][1];
					}
					
					max2 = max2 + lines[i][2];
				}
				max0 = max0 + lines[i][0];
			}
			else {
				max0 = max1 + lines[i][0];
				if (max1 <= max2) { // max0 <= max1 <= max2
					max1 = max2 + lines[i][1];
					max2 = max2 + lines[i][2];
				}
				else { // max0 <= max1 && max1 > max2
					max2 = max1 + lines[i][2];
					max1 = max1 + lines[i][1];
				}
			}
			if (min0 < min1) {
				if (min1 < min2) { // min0 < min1 < min2
					min2 = min1 + lines[i][2];
					min1 = min0 + lines[i][1];
				}
				else {
					if (min0 < min2) { // min0 < min2 <= min1
						min1 = min0 + lines[i][1];
					}
					else { // min2 <= min0 < min1
						min1 = min2 + lines[i][1];
					}
					min2 = min2 + lines[i][2];
				}
				min0 = min0 + lines[i][0];
			}
			else {
				min0 = min1 + lines[i][0];
				if (min1 >= min2) { // min0 >= min1 >= min2
					min1 = min2 + lines[i][1];
					min2 = min2 + lines[i][2];
				}
				else { // min0 >= min1 && min1 < min2
					min2 = min1 + lines[i][2];
					min1 = min1 + lines[i][1];
				}
			}
		}
		
		int max = Math.max(max0, Math.max(max1, max2));
		int min = Math.min(min0, Math.min(min1, min2));
		
		System.out.println(max+" "+min);
	}
}
