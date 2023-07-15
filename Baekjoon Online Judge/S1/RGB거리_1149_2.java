//BOJ S1
package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리_1149_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] color = new int[n+1][4];
        int[][] dp = new int[n+1][4];
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = color[1][1];
        dp[1][2] = color[1][2];
        dp[1][3] = color[1][3];
        for(int i=2; i<=n; i++) {
            dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + color[i][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + color[i][2];
            dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + color[i][3];
        }
        System.out.println(Math.min(dp[n][1], Math.min(dp[n][2], dp[n][3])));
    }
}
