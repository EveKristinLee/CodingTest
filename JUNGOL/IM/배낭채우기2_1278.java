package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배낭채우기2_1278 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] weights = new int[N+1];
		int[] profits = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			profits[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][W+1];
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=W; j++) {
				if(weights[i] <= j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]] + profits[i]);					
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N][W]);
	}
}
