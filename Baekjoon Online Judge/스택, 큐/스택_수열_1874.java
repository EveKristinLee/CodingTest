package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 스택_수열_1874 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> st = new Stack<>();  
		int[] arr = new int[n + 1];
		int idx = 1;
				
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<=n; i++) {
			st.push(i);
			sb.append("+").append("\n");
			while(!st.isEmpty() && st.peek() == arr[idx]) {
				st.pop();
				sb.append("-").append("\n");
				idx++;
			}
		}
		if(st.isEmpty()) {
			System.out.println(sb);
		}
		else {
			System.out.println("NO");
		}
	}
}
