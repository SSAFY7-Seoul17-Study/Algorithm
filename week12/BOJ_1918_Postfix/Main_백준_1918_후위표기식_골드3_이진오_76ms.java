import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Main_백준_1918_후위표기식_골드3_이진오_76ms
 * 
 * 중위 표기식 => 후위 표기식 구현
 * 
 * - 자료 구조
 *   1) 피연산자 스택 = Stack<String> operands
 *   2) 연산자 스택 = Stack<Character> operators
 *   
 * - 알고리즘
 *   : 중위 표현식의 각 토큰(피연산자 혹은 연산자 혹은 괄호)에 대하여
 *   
 *     1) 피연산자일 경우
 *       => 피연산자 스택에 push
 *     
 *     2) 여는 괄호일 경우
 *       => 연산자 스택에 push
 *     
 *     3) 닫는 괄호일 경우
 *       => 연산자 스택의 peek값이 여는 괄호일 경우, 연산자 스택에서 pop
 *       => 나머지 경우, flush, continue
 *     
 *     4) 연산자일 경우
 *       => 연산자 스택이 비어있는 경우, 연산자 스택에 push
 *       => 연산자 스택의 peek값의 우선 순위가 더 낮은 경우, 연산자 스택에 push
 *       => 나머지 경우, flush, continue
 *   
 *     5) flush 메서드 수행 이후
 *       => 다음 토큰으로 넘어가지 않고, 현재 토큰에 머무른다.
 *     
 *     6) push 메서드 수행 이후
 *       => 다음 토큰으로 넘어간다.
 *   
 *   : 모든 토큰에 대해 완료한 이후
 *   
 *     1) 연산자 스택이 빌 때까지, flush 연산을 계속 진행
 *     2) 피연산자 스택의 첫 번째 값을 reverse()하여 반환
 *   
 *   : flush 연산
 *     
 *     1) 연산자 스택에서 pop
 *     2) 피연산자 스택에서 pop (오른쪽 피연산자)
 *     3) 피연산자 스택에서 pop (왼쪽 피연산자)
 *     4) (1) + (2) + (3)을 피연산자 스택에 push
 *     
 */
public class Main_백준_1918_후위표기식_골드3_이진오_76ms {
	
	/** 우선 순위 배열 */
	private static int[] priority = new int[128];
	
	/** 중위 표기식, 피연산자 스택, 연산자 스택 */
	private static String expr;
	private static Stack<String> operands;
	private static Stack<Character> operators;
	
	/**
	 * @return 후위 표기식 빌드
	 */
	private static String build() {
		int index = 0;
		
		while (index < expr.length()) {
			char ch = expr.charAt(index);
			
			if (ch >= 'A' && ch <= 'Z') {
				operands.push("" + ch);
			}
			
			else {
				if (operators.isEmpty() || ch == '(') {
					operators.push(ch);					
				}
				else if (ch == ')') {
					if (operators.peek() == '(')
						operators.pop();
					else {
						flush();
						continue;
					}
				}
				else if (priority[operators.peek()] < priority[ch]) {
					operators.push(ch);
				}
				else {
					flush();
					continue;
				}
			}
			
			index++;
		}
		
		while (!operators.isEmpty())
			flush();
		
		String postExpr = new StringBuilder(operands.pop()).reverse().toString();
		
		return postExpr;
	}
	
	private static void flush() {
		StringBuilder expr = new StringBuilder();
		
		expr
		.append(operators.pop())
		.append(operands.pop())
		.append(operands.pop());
		
		operands.push(expr.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 중위 표기식
		expr = in.readLine();
		
		// 우선 순위 배열 초기화
		priority['+'] = 1;
		priority['-'] = 1;
		priority['*'] = 2;
		priority['/'] = 2;
		
		// 피연산자, 연산자 배열 초기화
		operands = new Stack<String>();
		operators = new Stack<Character>();
		
		System.out.println(build());
	}
}
