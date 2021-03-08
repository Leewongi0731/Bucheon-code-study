package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*13:23~14:20
*/
public class boj1918_후위표기식 {
	static int precedence(char ch) { // 우선순위
		if (ch == '(')
			return 0;
		if (ch == '+' || ch == '-')
			return 1;
		else
			return 2;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();

		StringBuilder sb = new StringBuilder();
		Stack<Character> ops = new Stack<>();

		for (int i = 0; i < input.length; i++) {
			char ch = input[i];
			if (ch >= 'A' && ch <= 'Z') {
				sb.append(ch);
				continue;
			} else if (ch == '(') {
				ops.push(ch);
			} else if (ch == ')') {
				while (ops.peek() != '(') {
					sb.append(ops.pop());
				}
				ops.pop();
			} else {
				while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
					sb.append(ops.pop());
				}
				ops.push(ch);
			}
		}

		while (!ops.isEmpty()) {
			sb.append(ops.pop());
		}
		
		System.out.println(sb.toString());
	}
}
