package solving.solve_1028;
//BOJ G4 9252 LCS 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9252 {
    static String s1;
    static String s2;
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        int len1 = s1.length();
        int len2 = s2.length();
        dp = new int[len1+1][len2+1];
        sb = new StringBuilder();

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        int i = len1;
        int j = len2;
        while(i>0 && j>0) {
            if(i==0 || j==0) {
                break;
            }
            if(dp[i][j] == dp[i-1][j]) {
                i--;
            }
            else if(dp[i][j] == dp[i][j-1]) {
                j--;
            }
            else{
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }
        }
        sb.reverse();
        if(sb.length() > 0) {
            System.out.println(sb.length());
            System.out.println(sb);
        }
        else {
            System.out.println(0);
        }
    }
}
