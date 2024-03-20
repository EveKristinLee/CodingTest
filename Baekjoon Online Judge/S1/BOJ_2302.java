package solving;
//BOJ S1 2302 극장 좌석

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2302 {
    static int N; //좌석 개수
    static int M; //vip 좌석
    static int[] vip;
    static long[] dp;
    static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        vip = new int[M+1];
        for(int i=1; i<=M; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }

        dp = new long[N+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }

        res = 1;
        for(int i=1; i<=M; i++) {
            res *= dp[(vip[i] - vip[i-1]) - 1];
        }
        res *= dp[(N - vip[M])];

        System.out.println(res);
    }
}
