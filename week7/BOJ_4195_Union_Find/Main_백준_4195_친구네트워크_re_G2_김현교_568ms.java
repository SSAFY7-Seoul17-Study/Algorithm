package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_백준_4195_친구네트워크_re_G2_김현교_568ms {

	static HashMap<String, Integer> mapping;
	static int[] parents;
	static int[] networks;
	static int idx;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mapping = new HashMap<>();
		
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			
			int F = Integer.parseInt(br.readLine());
			parents = new int[2 * F];
			networks = new int[2 * F];
			
			while (F-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				
				Integer index1 = getIndex(f1);
				Integer index2 = getIndex(f2);
				
				sb.append(union(index1, index2)).append("\n");
			}
			
			mapping.clear();
			idx = 0;
		}
		System.out.print(sb.toString());
	}//end main

	private static Integer getIndex(String name) {
		Integer index = mapping.get(name);
		if (index == null) {
			mapping.put(name, idx);
			parents[idx] = idx;
			networks[idx] = 1;
			index = idx++;;
		}
		return index;
	}

	static int union(int idx1, int idx2) {
		int root1 = findSet(idx1);
		int root2 = findSet(idx2);
		int n1 = networks[root1];
		int n2 = networks[root2];
		if (root1 == root2)
			return n1;
		networks[root1] = n1 + n2;
		parents[root2] = root1;
		return n1 + n2;
	}
	
	static int findSet(int index) {
		if (parents[index] == index)
			return index;
		return parents[index] = findSet(parents[index]);
	}
}//end class

