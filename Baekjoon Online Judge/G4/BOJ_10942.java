package solving.solve_1028;
//BOJ G4 10942 팰린드롬?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942 {
    static int N;
    static int M;
    static int[] nums;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        dp = new boolean[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = true;
        }
        for(int i=1; i<=N-1; i++) {
            if(nums[i] == nums[i+1]) {
                dp[i][i+1] = true;
            }
        }
        for(int k=2; k<=N-1; k++) { //떨어질 칸의 수
            for(int i=1; i<=N-k; i++) {
                int j = i+k;
                if(nums[i] == nums[j] && dp[i+1][j-1]) {
                    dp[i][j] = true;
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(dp[s][e]) {
                sb.append("1").append("\n");
            }
            else {
                sb.append("0").append("\n");
            }
        }

        System.out.println(sb);
    }
}
