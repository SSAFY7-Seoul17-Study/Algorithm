package Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_백준_1918_후위표기식_G3_김현교_76ms {
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		Stack<Operator> opStack = new Stack<>();
		
		for (int idx = 0; idx < line.length(); idx++) {
			char symbol = line.charAt(idx);
			int curRank = isOperator(symbol);
			
			if (curRank < 0) {
				sb.append(symbol);
				continue;
			}
			if (curRank == 0) {
				if (symbol == '(') {
					opStack.push(new Operator(symbol, curRank));
				} else {
					while (opStack.size() > 0) {
						Operator op = opStack.pop();
						if (op.symbol == '(')
							break;
						sb.append(op.symbol);
					}
				}
				continue;
			}
			
			while (opStack.size() != 0 && opStack.peek().rank >= curRank)
				sb.append(opStack.pop().symbol);
			opStack.push(new Operator(symbol, curRank));
		}
		while (opStack.size() > 0) {
			sb.append(opStack.pop().symbol);
		}
		System.out.println(sb.toString());
	}//end main
	
	static int isOperator(char c) {
		switch (c) {
		case '*': case '/':
			return 2;
		case '+': case '-':
			return 1;
		case '(': case ')':
			return 0;
		default:
			return -1;
		}
	}
	
	static class Operator {
		char symbol;
		int rank;

		public Operator(char symbol, int rank) {
			this.symbol = symbol;
			this.rank = rank;
		}
	}
}
/*
 * 피연산자는 바로 출력
 * 
 * 연산자는 스택 이용
 * 	- 스택 안이 비었거나, 현재보다 우선순위가 낮으면 그냥 넣기
 * 	- 스택 안의 것이 우선순위가 높거나 같으면 스택 안의 것을 출력
 * 
 * */

