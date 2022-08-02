import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_����_18808_��ƼĿ���̱�_���3_������_140ms
 * 
 *  - ����
 *    1. N x M ũ���� �迭 paper
 *      : ��ƼĿ�� ���� ��Ʈ��
 *    2. R x C ũ���� �迭 sticker
 *      : �ð���� 90�� ȸ�� ����
 *      : paper �迭�� ���� �� �ִ��� Ȯ���� �� �ִ� ����
 *        => paper �迭�� ���̴� ����
 *           => sticker�� ĭ ���� Ȯ���ϴ� ����
 * 
 */
public class Main_����_18808_��ƼĿ���̱�_���3_������_140ms {
	
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
			
			// ��ƼĿ ĭ ��
			int cnt = 0;
			
			// sticker �Է�
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int c = 0; c < C; c++) {
					int val = Integer.parseInt(st.nextToken());
					sticker[r][c] = val;
					
					cnt += val;
				}
			}
			
			// sticker ���̱�
l1:			for (int i = 0; i < 4; i++) { // ���� 0, 90, 180, 270
	
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
				
				// 90�� ȸ�� ����
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
		// ��ƼĿ�� ���̴� ���� �������� �Ǻ��ϴ� ����
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (sticker[r][c] == 1 && paper[offsetR+r][offsetC+c] == 1) {
					return false;
				}
			}
		}
		
		// ���̴� ����
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
