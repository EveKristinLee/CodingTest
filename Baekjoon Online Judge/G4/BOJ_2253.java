package solving.solve_1028;
//BOJ G4 2253 점프

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2253 {
    static int N; //돌의 개수
    static int M; //작은 돌의 개수
    static int[][] dp;
    static boolean[] chk;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //등차수열의 합 -> 근사값
        int v = (int) Math.pow(2*N, 0.5) + 2;
        dp = new int[N+1][v];
        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], INF);
        }
        chk = new boolean[N+1];
        for(int i=0; i<M; i++) {
            chk[Integer.parseInt(br.readLine())] = true;
        }

        dp[1][0] = 0;
        for(int i=2; i<=N; i++) {
            if(chk[i]) {
                continue;
            }
            for(int j=1; j<(int)Math.pow(2*i, 0.5)+1; j++) {
                dp[i][j] = Math.min(dp[i-j][j-1], Math.min(dp[i-j][j], dp[i-j][j+1])) + 1;
            }
        }

        int res = INF;
        for(int i=1; i<v; i++) {
            res = Math.min(res, dp[N][i]);
        }
        if(res == INF) {
            System.out.println(-1);
        }
        else {
            System.out.println(res);
        }
    }
}
