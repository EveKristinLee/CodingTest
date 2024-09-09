package com.company;
//BOJ S5 9625 BABBA

import java.util.Scanner;

public class BOJ_9625 {
    static int K;
    static int[] A;
    static int[] B;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        A = new int[K+1 + 2];
        B = new int[K+1 + 2];
        A[2] = 1;
        B[1] = 1;
        B[2] = 1;
        if(K >= 3) {
            for(int i=3; i<=K; i++) {
                A[i] = A[i-1] + A[i-2];
                B[i] = B[i-1] + B[i-2];
            }
        }
        System.out.println(A[K] + " " + B[K]);
    }
}
