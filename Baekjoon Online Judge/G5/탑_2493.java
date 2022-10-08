package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑_2493 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<Integer[]> stack = new Stack<>(); //[idx, height]
		int[] ans = new int[n+1];
 		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
 			int num = Integer.parseInt(st.nextToken());
 			while(!stack.isEmpty()) {
 				if(stack.peek()[1] < num) {
 					stack.pop();
 				}
 				else {
 					ans[i] = stack.peek()[0];
 					stack.push(new Integer[] {i, num});
 					break;
 				}
 			}
 			if(stack.isEmpty()) {
 				stack.push(new Integer[] {i, num});
 			}
 		}
		for(int i=1; i<=n; i++) {
			System.out.print(ans[i] + " ");
		}
	}

}
