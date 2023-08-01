//BOJ S3
package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파도반_수열_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for(int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            if(N > 3) {
                for(int i=4; i<=N; i++) {
                    dp[i] = dp[i-2] + dp[i-3];
                }
            }
            System.out.println(dp[N]);
        }
    }
}
