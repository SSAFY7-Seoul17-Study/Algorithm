import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * 이분탐색을 통한 구현
 * @author kit938639
 *
 */
public class Main_BOJ_10815_숫자카드_S4_김인태_1320ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);	//	이분탐색을 하기 위해서는 비교할 자료들이 정렬이 되어 있어야 한다.	
		
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp<arr[0] || arr[N-1]<tmp)	//	정렬이 되어 있으므로 새로 들어오는 숫자가 범위를 벗어났으면 검사 없이 0으로
				sb.append(0).append(" ");
			else
				sb.append(bis(arr, tmp)).append(" ");	//	탐색 시작
		}
		System.out.println(sb);
	}	//	end of main

	// 이분 탐색 
	public static int bis(int[] arr, int M) {
		int l = 0;
		int r = arr.length - 1;
		
		while(l <= r) {		// 왼쪽과 오른쪽이 겹치면 더이상 탐색안함.
			int mid = (l+r)/2;
			if(arr[mid]==M) {	//	값을 자료에서 찾음
				return 1;
			}else if(arr[mid] < M) {	//	입력 값이 현재 범위보다 큰 경우, 왼쪽 범위를 축소 시킴.
				l = mid + 1;
			}else {	// M < arr[mid]		//	입력 값이 현재 범위보다 작은 경우, 오른쪽 범위를 축소 시킴.
				r = mid - 1;
			}
		}
		return 0;	//	여기까지 도달하면 만족하는 값을 찾지 못한 것.
	}

}
