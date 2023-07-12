//BOJ G5
package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 호텔_1106 {

    public static void main(String[] args) throws IOException {
        int C;
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[] dp = new int[C+101]; //한번에 얻을 수 있는 고객의 수가 최대 100
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int pay = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            for(int j=customer; j<C+101; j++) {
                dp[j] = Math.min(dp[j], pay + dp[j-customer]);
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i=C; i<C+101; i++) {
            res = Math.min(res, dp[i]);
        }
        System.out.println(res);
    }
}
