import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_백준_18808_스티커붙이기_골드3_이진오_140ms
 * 
 *  - 구현
 *    1. N x M 크기의 배열 paper
 *      : 스티커를 붙일 노트북
 *    2. R x C 크기의 배열 sticker
 *      : 시계방향 90도 회전 로직
 *      : paper 배열에 붙일 수 있는지 확인할 수 있는 로직
 *        => paper 배열에 붙이는 로직
 *           => sticker의 칸 수를 확인하는 로직
 * 
 */
public class Main_백준_18808_스티커붙이기_골드3_이진오_140ms {
	
	private static int[][] paper;
	private static int N;
	private static int M;
	private static int totalCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[R][C];
			int[][] reverse = new int[C][R];
			
			// 스티커 칸 수
			int cnt = 0;
			
			// sticker 입력
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int c = 0; c < C; c++) {
					int val = Integer.parseInt(st.nextToken());
					sticker[r][c] = val;
					
					cnt += val;
				}
			}
			
			// sticker 붙이기
l1:			for (int i = 0; i < 4; i++) { // 방향 0, 90, 180, 270
	
				for (int offsetR = 0; offsetR <= N-R; offsetR++) {
					for (int offsetC = 0; offsetC <= M-C; offsetC++) {
						if (attach(offsetR, offsetC, R, C, sticker)) {
							totalCnt += cnt;
							break l1;
						}
					}
				}
				
				if (i == 3) {
					break;
				}
				
				// 90도 회전 로직
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						reverse[c][R-1-r] = sticker[r][c];
					}
				}
				
				int temp = R;
				R = C;
				C = temp;
				
				int[][] tempArr = sticker;
				sticker = reverse;
				reverse = tempArr;
			}
		}
		
		System.out.println(totalCnt);
	}
	
	public static boolean attach(int offsetR, int offsetC, int R, int C, int[][] sticker) {
		// 스티커를 붙이는 것이 가능한지 판별하는 로직
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (sticker[r][c] == 1 && paper[offsetR+r][offsetC+c] == 1) {
					return false;
				}
			}
		}
		
		// 붙이는 로직
		for (int dr = 0; dr < R; dr++) {
			for (int dc = 0; dc < C; dc++) {
				if (sticker[dr][dc] == 1) {
					paper[offsetR+dr][offsetC+dc] = 1;
				}
			}
		}
		
		return true;
	}

}
