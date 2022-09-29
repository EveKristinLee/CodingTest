package solving.solve_0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

//JO IM
public class 두_줄로_타일_깔기_1411 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2; i<=n; i++) {
			dp[i] = (dp[i-1] + dp[i-2] * 2) % 20100529;
		}
		System.out.println(dp[n]);
	}

}
