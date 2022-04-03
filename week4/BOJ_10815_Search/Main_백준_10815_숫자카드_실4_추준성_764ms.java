import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_10815_숫자카드_실4_추준성_764ms {
	
	public static void main(String[] args) throws Exception{
		/*
		 * "입력 받는 숫자 == checkArr의 인덱스 값" (입력 데이터로 하여금 대응되는 값에 O(1)로 접근하기 위함)
		 */
		int[] checkArr = new int[20000001]; // -10,000,000 ~ 10,000,000 (0포함)을 인덱스로 표현하기 위해 20,000,001 * 4 만큼의 메모리 공간 할당
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 상근이가 가지고 있는 카드 번호를 입력 받으면서 해당 숫자를 인덱스로 하는 checkArr 배열 값을 1로 할당
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			checkArr[Integer.parseInt(st.nextToken()) + 10000000] = 1;
		}
		
		// 문제로 주어지는 카드 번호를 입력 받으면서 해당 숫자를 인덱스로 하는 checkArr 배열 값이 1이면 1을 출력, 0이면 0을 출력
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {		
			if(checkArr[Integer.parseInt(st.nextToken()) + 10000000] == 1) sb.append(1).append(" "); // 
			else sb.append(0).append(" ");
		}
		
		System.out.println(sb.toString());
		
	} // end of main
} // end of class
