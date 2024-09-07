package homework;
//BOJ B2 2747 피보나치 수

import java.util.Scanner;

public class BOJ_2747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] mem = new int[N+1];
        mem[0] = 0;
        mem[1] = 1;
        for(int i=2; i<=N; i++) {
            mem[i] = mem[i-1] + mem[i-2];
        }
        System.out.println(mem[N]);
    }
}
