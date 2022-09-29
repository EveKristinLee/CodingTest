package solving.solve_0928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전_2_2294 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n, k;
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int[] num = new int[n];
		int[] dp = new int[k+1];
		
		for(int i=1; i<=k; i++) {
			dp[i] = 987654321;
		}
	
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 0;
		for(int i=0; i<n; i++) {
			for(int j=num[i]; j<=k; j++) {
				dp[j] = Math.min(dp[j], dp[j-num[i]] + 1);
			}
		}
		if(dp[k] == 987654321) {
			System.out.println(-1);
		}else {
			System.out.println(dp[k]);
		}
	}
}
