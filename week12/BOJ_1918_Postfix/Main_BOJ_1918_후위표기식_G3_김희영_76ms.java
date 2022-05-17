package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_1918_후위표기식_G3_김희영_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		StringBuilder sb = new StringBuilder();
		Stack<Character> st = new Stack<>();
		int size = str.length();

		for (int i = 0; i < size; i++) {
			char c = str.charAt(i);

			switch (c) {
			case '+':
			case '-':
			case '*':
			case '/':
				while (st.size() > 0 && priority(st.peek()) >= priority(c))
					sb.append(st.pop());
				st.add(c);
				break;

			case '(':
				st.add(c);
				break;

			case ')':
				while (st.size() > 0 && st.peek() != '(')
					sb.append(st.pop());
				st.pop();
				break;

			default:
				sb.append(c);
				break;
			}
		}

		while (st.size() > 0)
			sb.append(st.pop());

		System.out.println(sb.toString());

	} // end of main

	private static int priority(char c) {
		if (c == '(' || c == ')')
			return 0;
		else if (c == '+' || c == '-')
			return 1;
		else if (c == '*' || c == '/')
			return 2;
		else
			return -1;
	}

} // end of class
