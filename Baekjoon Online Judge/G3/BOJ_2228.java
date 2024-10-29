package solving.solve_0925;
//BOJ G3 2228 구간 나누기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2228 {
    static int N;
    static int M;
    static int[] num;
    static int[][] dp;
    static final int MIN = (-32768 * 101);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N+1];
        dp = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            num[i] = num[i-1] + Integer.parseInt(br.readLine());
        }
        for(int i=0; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                dp[i][j] = MIN;
            }
        }

        dp[1][1] = num[1];
        for(int i=2; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                dp[i][j] = dp[i-1][j]; //i번째 숫자가 j번째 구간에 포함 x

                if(j == 1) {
                    //j==1이면 첫 구간이므로 처음부터 i까지의 원소합을 고려해야함.
                    dp[i][j] = Math.max(dp[i][j], num[i]);
                }
                for(int k=0; k<=(i-2); k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j-1] + num[i] - num[k+1]);
                }
            }
        }
        System.out.println(dp[N][M]);
    }
}
