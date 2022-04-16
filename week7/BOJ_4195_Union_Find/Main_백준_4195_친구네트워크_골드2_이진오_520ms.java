import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Main_백준_4195_친구네트워크_골드2_이진오_520ms
 * 
 *  - 자료구조
 *    0) class Network
 *      : size - 네트워크의 사이즈
 *      : p    - 부모 네트워크의 번호
 *      
 *    1) HashMap<String, Integer> map
 *      : id - network의 번호
 *      
 *    2) ArrayList<Network> networks
 *      : Network 객체의 리스트
 *    
 *  - 알고리즘
 *    0) size 관리
 *      : 네트워크의 size는, 루트 네트워크에 저장
 *      
 *    1) find
 *      : 네트워크 객체의 p를 통해 루트 네트워크를 찾는 알고리즘 구현
 *      
 *    2) union
 *      : 두 네트워크의 합집합 연산을 구현
 *      : size 정보에 따라서, 큰 집합에 작은 집합을 추가
 * 
 */
public class Main_백준_4195_친구네트워크_골드2_이진오_520ms {
	
	static class Network {
		int size;
		int p;
		public Network(int size, int p) {
			this.size = size;
			this.p = p;
		}
	} // class Network

	public static int find(int no, ArrayList<Network> networks) {
		Network net = networks.get(no);
		if (no == net.p)
			return no;
		
		return net.p = find(net.p, networks);
	} // end of find
	
	public static int union(int no1, int no2, ArrayList<Network> networks) {
		int root1 = find(no1, networks);
		int root2 = find(no2, networks);
		if (root1 == root2) // 합집합 실패
			return 0;
		Network net1 = networks.get(root1);
		Network net2 = networks.get(root2);
		if (net1.size > net2.size) { // no1에 해당하는 네트워크가 더 큰 경우
			net2.p = root1;
			net1.size += net2.size;
			return 1;
		}
		else { // no2에 해당하는 네트워크가 더 큰 경우
			net1.p = root2;
			net2.size += net1.size;
			return 2;
		}
	} // end of union
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		int networkNo = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<Network> networks = new ArrayList<Network>();
		
		while(T-- > 0) {
			// 자료구조 초기화
			networkNo = 0;
			map.clear();
			networks.clear();
			
			int F = Integer.parseInt(in.readLine()); // F <= 100,000
			
			while (F-- > 0) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				
				String id1 = st.nextToken();
				String id2 = st.nextToken();
				
				boolean id1Exist = map.containsKey(id1);
				boolean id2Exist = map.containsKey(id2);
				
				int size = 0;
				if (!id1Exist && !id2Exist) { // id1, id2 모두 새로운 사용자인 경우
					Network net = new Network(2, networkNo);
					map.put(id1, networkNo);
					map.put(id2, networkNo);
					networks.add(net);
					size = 2;
				} else if (!id1Exist) { // id2는 기존 사용자, id1은 새로운 사용자인 경우
					int no = map.get(id2);
					map.put(id1, no);
					Network net = networks.get(find(no, networks));
					size = ++net.size;
				} else if (!id2Exist) { // id1은 기존 사용자, id2는 새로운 사용자인 경우
					int no = map.get(id1);
					map.put(id2, no);
					Network net = networks.get(find(no, networks));
					size = ++net.size;
				} else { // id1, id2 모두 기존 사용자인 경우
					int no1 = map.get(id1);
					int no2 = map.get(id2);
					
					int n = union(no1, no2, networks);
					Network net = null;
					if (n <= 1) {
						net = networks.get(find(no1, networks));
						size = net.size;
					} else {
						net = networks.get(find(no2, networks));
						size = net.size;
					}
				}
				out.append(size).append("\n");
			} // end of while F
			
		} // end of while T
		
		System.out.print(out.toString());
		
	} // end of main

} // end of class
