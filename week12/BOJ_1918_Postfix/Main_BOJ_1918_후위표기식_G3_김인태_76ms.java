import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


/**
 * 피연산자는 출력
 * 연산자는 스택 확인 후 
 * 들어오는 연산자의 우선순위 > 스택 꼭대기의 연산자의 우선순위 >> push
 * 반대로 
 * 들어오는 연산자의 우선순위 <= 스택 내부가 >> 작아질 때 까지 스택에서 pop하며 출력
 * 이 때 )는 출력 x
 * @author kit938639
 *
 */
public class Main_BOJ_1918_후위표기식_ {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] exp = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<exp.length; i++) {
//			피연산자는 출력
			if('A' <= exp[i] && exp[i] <= 'Z') {
				sb.append(exp[i]);
				continue;
			}
//			여는 괄호는 스택에 push
			if(exp[i] == '(') {
				stack.push('(');
				continue;
			}
//			닫는 괄호의 경우

			if(exp[i] == ')') {
//				스택이 비어있지 않고 여는 괄호를 만나기 전까지 출력
				while(!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
//				여는 괄호를 만나면 한 쌍의 괄호이고 출력하지는 않을 것이므로 pop
				if(!stack.isEmpty()) stack.pop();
				continue;
			}
//			연산자라면 스택이 비어있지 않고
//			스택 꼭대기의 우선순위가 현재보다 현재 연산자보다 우선순위가 크거나 같으면 출력해야 하는 연산자이므로 출력
			while(!stack.isEmpty() && check(stack.peek()) >= check(exp[i])) {
				sb.append(stack.pop());
			}
//			우선순위가 작은 연산자를 만나면 스택에 들어갈 차례이므로 push
			stack.push(exp[i]);
		}
//		일련의 과정 거치고 남은 나머지 연산자들 모두 출력
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}

	private static int check(char s) {
		switch (s) {
		case '*': 
		case '/':
			return 2;			
		case '+':		
		case '-':
			return 1;
		default:
			return 0;
		}
	}

}
