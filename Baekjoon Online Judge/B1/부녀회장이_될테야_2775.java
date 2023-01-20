package solve01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ B1
public class 부녀회장이_될테야_2775 {
    static int k;
    static int n;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- >0) {
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());
            dp = new int[k+1][n+1];
            for(int i=1; i<=n; i++) {
                dp[0][i] = i;
            }
            for(int i=1; i<=k; i++) {
                for(int j=1; j<=n; j++) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
            sb.append(dp[k][n] + "\n");
        }
        System.out.println(sb);
    }
}
