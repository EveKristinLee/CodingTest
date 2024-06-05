package solving.solve_1005;
//BOJ G1 1509 팰린드롬 분할

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1509 {
    static int[] dp;
    static Character[] s;
    static boolean[][] isPalin;
    static int len;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        len = input.length();
        s = new Character[len+1];
        isPalin = new boolean[len+1][len+1];
        dp = new int[len+1];
        Arrays.fill(dp, INF);

        for(int i=1; i<=len; i++) {
            s[i] = input.charAt(i-1);
            isPalin[i][i] = true;
        }
        isPalindrome();
        dp();
        System.out.println(dp[len]);
    }

    private static void isPalindrome() {
        for(int i=1; i<=len-1; i++) {
            if(s[i] == s[i+1]) {
                isPalin[i][i+1] = true;
            }
        }
        for(int k=2; k<=len-1; k++) {
            for(int i=1; i<=len-k; i++) {
                int j = i+k;
                if(s[i] == s[j] && isPalin[i+1][j-1]) {
                    isPalin[i][j] = true;
                }
            }
        }
    }

    private static void dp() {
        dp[0] = 0;
        for(int end = 1; end<=len; end++) {
            for(int front = 1; front<=end; front++) {
                if(isPalin[front][end]) {
                    dp[end] = Math.min(dp[end], dp[front-1]  + 1);
                }
            }
        }
    }
}
