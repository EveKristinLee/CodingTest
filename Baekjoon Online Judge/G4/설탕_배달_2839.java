//BOJ S4
package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 설탕_배달_2839 {
    public static void main(String[] args) throws IOException {
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        if(N < 5) {
            if(N != 3) {
                System.out.println(-1);
            }
            if(N == 3) {
                System.out.println(1);
            }
            return;
        }
        dp[3] = 1;
        dp[5] = 1;
        for(int i=6; i<=N; i++) {
            if(dp[i-3] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i-3] + 1);
            }
            if(dp[i-5] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i-5] + 1);
            }
        }
        if(dp[N] == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(dp[N]);
        }
    }
}
