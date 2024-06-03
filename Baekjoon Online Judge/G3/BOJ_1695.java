package solving.solve_0925;
//BOJ G3 1695 팰린드롬 만들기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1695 {
    static int N;
    static int[] nums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        dp = new int[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int res = dp(1, N);
        System.out.println(res);

    }

    private static int dp (int left, int right) {
        if(left >= right) {
            return 0;
        }
        if(dp[left][right] != -1) {
            return dp[left][right];
        }

        int cnt = 0;
        if(nums[left] == nums[right]) {
            cnt = dp(left+1, right-1);
        }
        else {
            cnt = dp(left+1, right) + 1;
            cnt = Math.min(cnt, dp(left, right-1) + 1);
        }

        return dp[left][right] = cnt;
    }
}

