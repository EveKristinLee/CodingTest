package solving.solve_0914;
//BOJ G5 4811 알약

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4811 {
    /*
    *   dp 1
    *   1.W H
    *
    *   dp 2
    *   1.W H W H
    *   2.W W H H
    *
    *   dp 3
    *   1. 작은 알약 -> 큰 알약 -> 큰 알약
    *       작은 알약을 먹고나면 큰 알약 두개가 남음
    *       => dp[2] * dp[0]
    *   2. 큰 알약 -> 작은 알약 -> 큰 알약
    *       큰 알약을 먹고나면, 작은 알약 -> 작은 알약 -> 큰 알약으로, 1가지 입니다.
    *       => dp[1] * dp[1]
    *   3. 큰 알약 -> 큰 알약 -> 작은 알약
    *       앞에 큰 알약 두개는 dp[2] 와 같고, 작은 알약은 케이스 구분에 영향을 끼치지 않는다
    *       => dp[2] * dp[0]
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long[] dp = new long[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=30; i++) {
            long cnt = 0;
            for(int j=0; j<i; j++) {
                cnt += dp[j] * dp[i-1-j];
            }
            dp[i] = cnt;
        }

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N==0) {
                break;
            }
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
