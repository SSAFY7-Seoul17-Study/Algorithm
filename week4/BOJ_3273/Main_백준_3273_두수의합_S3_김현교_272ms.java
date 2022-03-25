package twoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_3273_두수의합_S3_김현교_272ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(nums);
		int cnt = 0;
		int left = 0;
		int right = n - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum <= x) {
				if (sum == x)
					cnt++;
				left++;
			} else {
				right--;
			}
		}
		System.out.println(cnt);
	}//end main
}
