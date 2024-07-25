package solving.solve_1028;
//BOJ G4 1749 점수따먹기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1749 {
    static int N;
    static int M;
    static long[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new long[N+1][M+1];
        dp = new long[N+1][M+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Long.parseLong(st.nextToken());
            }
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                dp[i][j] = map[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        long res = Long.MIN_VALUE;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                for(int x=i; x<=N; x++) {
                    for(int y=j; y<=M; y++) {
                        long now = calcSum(i, j, x, y);
                        res = Math.max(res, now);
                    }
                }
            }
        }

        System.out.println(res);
    }

    private static long calcSum(int x1, int y1, int x2, int y2) {
        return dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
    }
}
