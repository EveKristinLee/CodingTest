package solving_2.solve_01.solve_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G5
public class 자두나무_2240 {

	static int T;
	static int W;
	static int[] drop;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		drop = new int[T+1];
		for(int i=1; i<=T; i++) {
			drop[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[T+1][3][W+1];
		
		if(drop[1] == 1) { //첫번재가 1이면 1번째 자두 겟
			dp[1][1][0] = 1;
			dp[1][2][1] = 0;
		}
		else if(drop[1] == 2) {
			dp[1][1][0] = 0;
			dp[1][2][1] = 1;
		}
		
		for(int i=2; i<=T; i++) {
			if(drop[i] == 1) {
				dp[i][1][0] = dp[i-1][1][0] + 1;
				dp[i][2][0] = dp[i-1][2][0];
				
				for(int w=1; w<=W; w++) {
					dp[i][1][w] = Math.max(dp[i-1][1][w], dp[i-1][2][w-1]) +1;
					dp[i][2][w] = Math.max(dp[i-1][1][w-1], dp[i-1][2][w]);
				}
			}
			else if(drop[i] == 2) {
				dp[i][1][0] = dp[i-1][1][0];
				dp[i][2][0] = dp[i-1][2][0] + 1;
				
				for(int w=1; w<=W; w++) {
					dp[i][1][w] = Math.max(dp[i-1][1][w], dp[i-1][2][w-1]);
					dp[i][2][w] = Math.max(dp[i-1][1][w-1], dp[i-1][2][w]) + 1;
				}
			}
		}
		
		int res = 0;
		for(int i=0; i<=W; i++) {
			res = Math.max(res, Math.max(dp[T][1][i], dp[T][2][i]));
		}
		System.out.println(res);
	}
}
