package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ S1
public class One로_만들기_2_12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] track = new int[N+1];

        dp[1] = 0;
        track[1] = -1;

        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1]+1;
            track[i] = i-1;

            if(i%2==0 && dp[i]>dp[i/2]+1) {
                dp[i] = dp[i/2]+1;
                track[i] = i/2;
            }
            if(i%3==0 && dp[i]>dp[i/3]+1) {
                dp[i] = dp[i/3]+1;
                track[i] = i/3;
            }
        }

        System.out.println(dp[N]);
        int idx = N;
        for(int i=0; i<=dp[N]; i++) {
            System.out.print(idx + " ");
            idx = track[idx];
        }
        System.out.println();
    }
}
