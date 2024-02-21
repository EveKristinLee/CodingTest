package solving.solve_0925;
//BOJ G3 7570 줄 세우기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7570 {
    static int N;
    static int[] dp;
    static int maxN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        maxN = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int c = Integer.parseInt(st.nextToken());
            dp[c] = dp[c-1] + 1; //1씩 증가하는 수열의 최대 길이
            maxN = Math.max(maxN, dp[c]);
        }
        System.out.println(N - maxN);
    }
}
