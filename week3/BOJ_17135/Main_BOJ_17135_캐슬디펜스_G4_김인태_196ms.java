import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_BOJ_17135_캐슬디펜스_G4_김인태_196ms {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean visit[][];
    static int direct[][] = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
    static int list[][], copylist[][], attacker[], rkill[][];
    static int N, M, D, ans, kidx;
 
    static void bfs() {
        int n = N;	//	궁수가 올라가므로 N행에서부터 시작
        int sum = 0;	//	죽인 적 수
        Deque<int[]> dq = new ArrayDeque<int[]>();
        ArrayList<int[]> kill = new ArrayList<>();
        while (true) {
            for (int k = 0; k < 3; k++) {	//	궁수 별로 적 탐색
                dq.addLast(new int[] { n, attacker[k], 0 });	//	N행, 시작하는 궁수 위치, 탐색 반경 
                for (int i = 0; i < n; i++)						//	방문 배열 초기화
                    Arrays.fill(visit[i], false);
 
                while (!dq.isEmpty()) {							
                    int x = dq.peekFirst()[0];		// n
                    int y = dq.peekFirst()[1];		//	attacker[k]
                    int cnt = dq.pollFirst()[2];	//	탐색 반경
 
                    if (cnt == D) {		//	궁수 한 명이 적을 찾을 수 있는 거리가 되면 해당 궁수는 할 일 다 함. 
                        dq.clear();		//	큐에서 제거하고 다음 궁수에게 바톤터치
                        break;
                    }
                    boolean chk = false;
                    for (int i = 0; i < 3; i++) {
                        int nx = x + direct[i][0];
                        int ny = y + direct[i][1];
 
                        if (nx < 0 || ny < 0 || nx >= n || ny >= M)		//	경계 	벗어나면 넘어감
                            continue;
                        if (visit[nx][ny])								//	방문 했으면 넘어감
                            continue;
                        if (copylist[nx][ny] == 1) {					//	적이 있다면
                            chk = true;
                            kill.add(new int[] { nx, ny, cnt + 1 });	//	해당 적 기록하고 탐색 탈출, 왜냐하면 가장 가까운 적이기 때문
                            break;
                        } else {										//	적이 없으면
                            dq.addLast(new int[] { nx, ny, cnt + 1 });	
                            visit[nx][ny] = true;
                        }
                    }
                    if (chk)		//	궁수가 적을 찾았기에 다음 궁수가 적을 찾아야 함.
                        break;
                }
                dq.clear();		//	원래 차례 이던 궁수 바톤 넘김
 
                if (kill.isEmpty())		//	적 제거 과정 시작, 없으면 넘어감
                    continue;
 
                rkill[kidx][0] = kill.get(0)[0];		//	적이 위치한 행
                rkill[kidx++][1] = kill.get(0)[1];		//	적이 위치한 열
                kill.clear();
            }	//	end of for 궁수 별 탐색
 
            for (int i = 0; i < kidx; i++) {
                if (copylist[rkill[i][0]][rkill[i][1]] == 1) {	//	해당 위치에 적이 있으면
                    copylist[rkill[i][0]][rkill[i][1]] = 0;		//	적을 제거
                    sum++;										//	제거한 적 수 추가
                }
            }
            kidx = 0;
 
            boolean isAllkill = true;			//	모든 적이 제거가 되었는가?	 제거되었다 가정
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < M; j++) {
                    if (copylist[i][j] == 1) {	//	왼쪽에서부터 탐색 n행에 적이 존재하면
                        isAllkill = false;		//	아직 적이 남아있다. (= 다 제거하지 못하고 남은 적 = 성에 도착한 적)
                        break;
                    }
                }
                if (!isAllkill)		//	적이 성에 도착
                    break;
            }	// end of for check allkill
            if (isAllkill)
                break;
            n--;
        }	//	end of while
 
        ans = Math.max(ans, sum);
    }	//	end of bfs
 
    static void func(int idx, int cnt) {	//	조합 코드
        if (cnt == 3) {						//	궁수의 위치가 결정되면
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++)
                    copylist[i][j] = list[i][j];
            }								//	 원본 배열 복사 후 탐색 시작
            bfs();							//	탐색
            return;
        }
 
        for (int i = idx; i < M; i++) {		//	MC3, M개의 자리 중 3자리
            attacker[cnt] = i;
            func(i + 1, cnt + 1);
        }
    }
 
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new int[N][M];			//	원본 배열
        copylist = new int[N][M];		//	조합별 사용 배열
        attacker = new int[3];			//	궁수 위치
        visit = new boolean[N][M];		//	적 체크
        rkill = new int[226][2];		//	MAX size인 15*15  행렬에서 표현가능한 좌표 수
 
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
        input();
        func(0, 0);
        System.out.println(ans);
    }
    
}

