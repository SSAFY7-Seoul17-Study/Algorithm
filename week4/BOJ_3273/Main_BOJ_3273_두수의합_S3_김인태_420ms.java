import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 투 포인터
 * @author kit938639
 *
 */
public class Main_BOJ_3273_두수의합_S3_김인태_420ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);	//	일관된 포인터 사용을 위해 정렬
		
		int l = 0;
		int r = N-1;
		int cnt=0;
		
		while(l < r) {
			if(arr[l] + arr[r] < x) {	//	합이 기준 값보다 작으면 왼쪽 포인터를 증가시킨다.
				l++;
			}
			else if(x < arr[l] + arr[r]){	//	합이 기준 값보다 크다면 오른쪽 포인터를 감소시킨다.
				r--;		
			}
			else {	//	같으면 카운트를 +1 하고 왼쪽, 오른쪽 포인터를 모두 이동시킨다.	
				cnt++;
				l++;
				r--;
			}
		}
		
		System.out.println(cnt);
	}

}
