package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사_2_15486 {
    static int N;
    static int[] T;
    static int[] P;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N+2];
        P = new int[N+2];
        dp = new int[N+2];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=N; i>=1; i--) {
            if(i + T[i] > N+1) {
                dp[i] = dp[i+1];
            }
            else {
                dp[i] = Math.max(dp[i+1], P[i] + dp[i+T[i]]);
            }
        }
        System.out.println(dp[1]);
    }
}
