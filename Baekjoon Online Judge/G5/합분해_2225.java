package solving.solve_1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G5
public class 합분해_2225 {

	static final int INF = 1000000000;
	static int N;
	static int K;
	static int[] select;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		select = new int[K];
		dp = new int[201][201];
		
		for(int i=1; i<=K; i++) {
			dp[i][0] = 1;
		}
		
		for(int i=1; i<=K; i++) {
			for(int j=1; j<=N; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % INF;
			}
		}
		
		System.out.println(dp[K][N] % INF);
	}

	
}
