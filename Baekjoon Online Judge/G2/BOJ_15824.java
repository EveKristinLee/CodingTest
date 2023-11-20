package solving_2.solve_12.solve_19;
//BOJ G2 너 봄에는 캡사이신이 맛있단다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15824 {
    static final int MOD = 1_000_000_007;
    static int N;
    static long[] menu;
    static long[] pow;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        menu = new long[N+1];
        pow = new long[N+1];
        pow[0] = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            menu[i] = Long.parseLong(st.nextToken());
            pow[i] = (pow[i-1] * 2) % MOD;
        }

        Arrays.sort(menu);

        res = 0;

        // 최대가 되는 경우의 모든 최대값 - 최소가 되는 경우의 모든 최소값
        for(int i=1; i<=N; i++) {
            res += MOD + (pow[i-1] - pow[N-i]) * menu[i] % MOD;
            res %= MOD;
        }
        System.out.println(res % MOD);
    }
}
