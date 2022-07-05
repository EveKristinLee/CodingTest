package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class 랜선자르기1654 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        long minSize = 1; //0으로 하면 0으로 나눠지는 문제 발생
        long maxSize = -987654321;
        long mid = 0;
        int[] lines = new int[k];

        for(int i=0; i<k; i++) {
            lines[i] = sc.nextInt();
        }
        Arrays.sort(lines);
        maxSize = lines[k-1];

        while(minSize <= maxSize) {
            long cnt = 0;
            mid = (minSize + maxSize) /2;
            for(int i=0; i< lines.length; i++) {
                cnt += lines[i]/mid;
            }
            if(cnt >= n) {
                minSize = mid+1;
            }
            else if (cnt < n) {
                maxSize = mid-1;
            }
        }
        System.out.println(maxSize);
    }
}