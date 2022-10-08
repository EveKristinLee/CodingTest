package solving.solve_0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 하노이_탑_이동_순서_11729 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb.append((int)Math.pow(2, N) - 1 + "\n");
		hanoi(N, 1, 2, 3);
		System.out.println(sb);
	}

	private static void hanoi(int n, int start, int mid, int to) {
		if(n == 1) {
			sb.append(start + " " + to + "\n");
			return;
		}
		
		hanoi(n-1, start, to, mid);
		
		sb.append(start + " " + to + "\n");
		
		hanoi(n-1, mid, start, to);
	}
}
