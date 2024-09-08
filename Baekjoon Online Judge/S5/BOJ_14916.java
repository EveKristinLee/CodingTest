package com.company;
//BOJ S5 14916 거스름돈

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14916 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1+6];
        final int INF = 987654321;
        Arrays.fill(dp, INF);
        dp[2] = 1;
        dp[4] = 2;
        dp[5] = 1;

        for(int i=6; i<=n; i++) {
            dp[i] = Math.min(dp[i-2], dp[i-5]) + 1;
        }

        if(dp[n] == INF) {
            System.out.println(-1);
        }
        else {
            System.out.println(dp[n]);
        }
    }
}
