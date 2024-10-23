package solving.solve_1028;

//BOJ G4 2631 줄세우기

import java.util.Scanner;

public class BOJ_2631 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] person = new int[N];
        for(int i=0; i<N; i++) {
            person[i] = sc.nextInt();
        }

        int[] dp = new int[N];
        int max = 0;
        for(int i=0; i<N; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(person[j] < person[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    max = Math.max(dp[i], max);
                }
            }
        }

        System.out.println(N - max);
    }
}
