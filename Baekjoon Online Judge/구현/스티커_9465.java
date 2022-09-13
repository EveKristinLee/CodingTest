package solving.solve_0911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-- >0) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[2+1][n+1];
			int[][] dp = new int[2+1][n+1];
			for(int i=1; i<=2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dp[1][1] = map[1][1];
			dp[2][1] = map[2][1];
			for(int i=2; i<=n; i++) {
				dp[1][i] = Math.max(dp[2][i-1], dp[2][i-2]) + map[1][i];
				dp[2][i] = Math.max(dp[1][i-1], dp[1][i-2]) + map[2][i];
			}
			
			sb.append(Math.max(dp[1][n], dp[2][n]) + "\n");
		}
		System.out.println(sb);
	}
}
