package solving.solve_0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 괄호_추가하기_16637 {

	static int n; 
	static int maxRes = Integer.MIN_VALUE;
	static ArrayList<Integer> num;
	static ArrayList<Character> ops;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String input = br.readLine();
		num = new ArrayList<>();
		ops = new ArrayList<>();
		for(int i=0; i<input.length(); i++) {
			if(i % 2 ==0) {
				num.add(input.charAt(i) - '0');
			}
			else {
				ops.add(input.charAt(i));
			}
		}
		
		dfs(0, num.get(0));
		System.out.println(maxRes);
	}

	private static void dfs(int idx, int sum) {
		if(idx == ops.size()) {
			maxRes = Math.max(sum, maxRes);
			return;
		}
		
		int calcRes = calc(ops.get(idx), sum, num.get(idx+1));
		dfs(idx+1, calcRes);
		
		if(idx+1 < ops.size()) { //이후의 계산식 미리 계산 
			int first = calc(ops.get(idx+1), num.get(idx+1), num.get(idx+2));
			int second = calc(ops.get(idx), sum, first);
			dfs(idx+2, second);
		}
	}

	private static int calc(char op, int a, int b) {
		int sum = 0;
		if(op == '+') {
			sum = a + b;
		}
		else if(op == '-') {
			sum = a - b;			
		}
		else if(op == '*') {
			sum = a * b;			
		}
		return sum;
	}
}
