import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_백준_11000_강의실배정_골드5_이진오_776ms
 * 
 *  - 그리디(Greedy) 
 *    0) N개의 강의 (s_i, t_i)를 강의실에 배정, 모든 수업을 가능하게 하는 최소 강의실의 개수
 *       => 강의실이 비는 시간 (v_i) <= 강의가 시작하는 시간 (s_i)를 만족할 경우 해당 강의실 사용 가능
 *       
 *    1) N개의 강의 = 시작 시간 기준 오름차순 정렬
 *    2) 강의실 = 비는 시간 기준 오름차순 정렬
 *       => 강의 (s_i, t_i)에 대하여, 
 *          : 강의실이 비는 시간 (v_i) <= 강의가 시작하는 시간 (s_i)일 경우 강의실 그대로 사용, PQ에 추가
 *          : 강의실이 비는 시간 (v_i) >  강의가 시작하는 시간 (s_i)일 경우 강의실 하나 추가, PQ에 둘 다 추가
 * 
 */
public class Main_백준_11000_강의실배정_골드5_이진오_776ms {
	
	private static int[][] lectures;
	private static PriorityQueue<int[]> rooms;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		lectures = new int[N][2];
		rooms = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]); // 끝나는 시간 기준 오름차순 정렬
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			lectures[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간 s
			lectures[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간 t
		}
		
		Arrays.sort(lectures, (o1, o2) -> o1[0] - o2[0]);
		
		System.out.println(solve());
	}
	
	public static int solve() {
		int cnt = 0;
		for (int[] lecture : lectures) {
			if (rooms.isEmpty()) {
				rooms.offer(lecture);
				cnt++;
			}
			else {
				int[] room = rooms.poll();
				
				if (lecture[0] < room[1]) { // 강의 시작 시간 < 강의실이 비는 시간
					rooms.offer(room);
					rooms.offer(lecture);
					cnt++;
				}
				else { // 강의 시작 시간 >= 강의실이 비는 시간
					room[1] = lecture[1];
					rooms.offer(room);
				}
			}
		}
		
		return cnt;
	}
}
