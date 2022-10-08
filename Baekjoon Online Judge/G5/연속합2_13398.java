package solving.solve_0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연속합2_13398 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] num = new int[n+1];
		int[][] dp = new int[2][n+1];
		int res = Integer.MIN_VALUE;
		for(int i=1; i<=n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		res = num[1];
		dp[0][1] = num[1];
		dp[1][1] = num[1];
		for(int i=2; i<=n; i++) {
			dp[0][i] = Math.max(dp[0][i-1] + num[i], num[i]);
			dp[1][i] = Math.max(dp[1][i-1] + num[i], dp[0][i-1]);
			res = Math.max(res, Math.max(dp[0][i], dp[1][i]));
		}
		System.out.println(res);
	}
}
