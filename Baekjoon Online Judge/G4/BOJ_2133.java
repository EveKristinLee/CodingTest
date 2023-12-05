package solving.solve_1028;
//BOJ G4 2133 타일 채우기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        if(N % 2 == 0) {
            dp[0] = 1;
            dp[1] = 0;
            dp[2] = 3;
            for(int i=4; i<=N; i++) {
                dp[i] = dp[i-2] * dp[2];
                for(int j=i-4; j>=0; j-=2) {
                    dp[i] += dp[j] * 2;
                }
            }
        }
        System.out.println(dp[N]);
    }
}
