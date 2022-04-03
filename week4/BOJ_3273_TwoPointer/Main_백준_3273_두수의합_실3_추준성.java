import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_3273_두수의합_실3_추준성 {
	
	// num   : 5, 12, 7, 10, 9, 1, 2, 3, 11 (존재하는 수)
	// x-num : 8, 1, 6, 3, 4, 12, 11, 10, 2 (필요한 수)
	
	// 필요로하는 수가 존재하면, boolean 배열에서 값 위치, index 위치에 해당하는 boolean값 false로 변환
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] flagArr = new boolean[2000001]; // 각 숫자의 존재성 여부 표시 배열
		int[] nums = new int[N+1]; // x-num
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			flagArr[num] = true; // num에 해당하는 숫자 존재성 true 할당
			nums[i] = num;
		}
		int x = Integer.parseInt(br.readLine());
		
		// x-num 입력
		for (int i = 1; i <= N; i++) {
			nums[i] = x - nums[i];
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			// x-num이 0보다 크고, nums[i]!=x-nums[i]이고, flagArr에 존재하면
			if(nums[i] > 0 && nums[i]!=x-nums[i] && flagArr[nums[i]]) {
				flagArr[nums[i]] = false;
				flagArr[x-nums[i]] = false;
				cnt++;
			}
		}		
		System.out.println(cnt);
	} // end of main
} // end of class
