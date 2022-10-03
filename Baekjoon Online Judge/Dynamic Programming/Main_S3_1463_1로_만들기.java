package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_S3_1463_1로_만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		
		for(int i=n-1; i>=1; i--) {
			dp[i] = dp[i+1] + 1;
			if(i*3 <= n) {
				dp[i] = Math.min(dp[i], dp[i*3] +1);
			}
			if(i*2 <= n) {
				dp[i] = Math.min(dp[i], dp[i*2] +1);
			}
		}
		System.out.println(dp[1]);
	}
}
