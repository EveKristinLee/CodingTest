package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장_긴_바이토닉_부분_수열_11054 {
    static int N;
    static int[] num;
    static int[] lis;
    static int[] lds;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        lis = new int[N];
        lds = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        LIS();
        LDS();
        int res = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            dp[i] = lis[i] + lds[i] -1;
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }

    private static void LIS() {
        for(int i=0; i<N; i++) {
            lis[i] = 1;
            for(int j=0; j<i; j++) {
                if(num[i]>num[j]) {
                    lis[i] = Math.max(lis[i], lis[j]+1);
                }
            }
        }
    }

    private static void LDS() {
        for(int i=N-1; i>=0; i--) {
            lds[i] = 1;
            for(int j=N-1; j>i; j--) {
                if(num[i]>num[j]) {
                    lds[i] = Math.max(lds[i], lds[j]+1);
                }
            }
        }
    }
}
