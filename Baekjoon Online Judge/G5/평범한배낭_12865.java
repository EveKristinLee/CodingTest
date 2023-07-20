//BOJ G5
package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //물품의 수
        int K = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게
        int[] W = new int[N+1];//물건의 무게
        int[] V = new int[N+1];//물건의 가치

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                if(W[i] > j) { //현재 담을 수 있는 무게보다 크다면 -> 담을 수 없음
                    dp[i][j] = dp[i-1][j];
                }
                else { //현재 담을 수 있는 무게보다 작다면
                    //이전에 담겨있던 무게 vs 현재 무게 + 현재 무게를 뺀 무게의 최대값
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}