package solving;
//BOJ S1 25822 2000문제 푼 임스

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25822 {
    static double C; //코인
    static int N;
    static int[][] dp;
    static int[] solve;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Double.parseDouble(br.readLine());
        N = Integer.parseInt(br.readLine());
        solve = new int[N+1];
        int cnt = (int) (C/0.99);
        if(cnt > 2) {
            cnt = 2;
        }
        dp = new int[N+1][cnt+1];
        int maxSolve = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            solve[i] = Integer.parseInt(st.nextToken());
            maxSolve = Math.max(maxSolve, solve[i]);
        }

        int longSolve = 0;
        for(int i=1; i<=N; i++) {
            if(solve[i] > 0) { //오늘 문제를 풀었다면, 전날 프리즈를 쓴 개수 + 1
                for(int j=0; j<=cnt; j++) {
                    dp[i][j] = dp[i-1][j] + 1;
                    longSolve = Math.max(longSolve, dp[i][j]);
                }
            }
            else {
                for(int j=1; j<=cnt; j++) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    longSolve = Math.max(longSolve, dp[i][j]);
                }
            }
        }

        System.out.println(longSolve);
        System.out.println(maxSolve);
    }
}
