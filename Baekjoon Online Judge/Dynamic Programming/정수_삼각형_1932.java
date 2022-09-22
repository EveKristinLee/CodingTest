package solving.solve_0922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수_삼각형_1932 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] num = new int[n+1][n+1];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<=i; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[n+1][n+1];
		dp[0][0] = num[0][0];
		int res = num[0][0];
		for(int i=1; i<n; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0) {
					dp[i][j] = dp[i-1][j] + num[i][j];
				}
				else if(j==i) {
					dp[i][j] = dp[i-1][j-1] + num[i][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + num[i][j];
				}
				if(i==n-1) {
					res = Math.max(res, dp[i][j]);
				}
			}
		}
		System.out.println(res);
	}
}
