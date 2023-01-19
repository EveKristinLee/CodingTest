package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G3
public class 소형기관차_2616 {
    static int N;
    static int M;
    static int[] arr;
    static int[] sum;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        sum = new int[N+1];
        dp = new int[4][N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + arr[i];
        }
        M = Integer.parseInt(br.readLine());

        for(int i=1; i<=3; i++) {
            for(int j=i*M; j<=N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + (sum[j] - sum[j-M]));
            }
        }

        System.out.println(dp[3][N]);
    }
}
