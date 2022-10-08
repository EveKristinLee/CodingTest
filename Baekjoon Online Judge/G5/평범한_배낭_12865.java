package solving.solve_0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한_배낭_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N;
		int K;
		int[] w;
		int[] v;
		int[][] dp;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		w = new int[N+1];
		v = new int[N+1];
		dp = new int[N+1][K+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=K; j++) {
				if(j >= w[i]) { //현재 담을 수 있는 무게보다 작다면
					//이전에 담겨있던 무게 vs 현재 무게 + 현재무게를 뺀 무게의 최댓값
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i]] + v[i]); 
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
