package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 성적통계5800 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k;
        int n;
        Integer[] score;

        k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            score = new Integer[n];
            for(int j=0; j<n; j++) {
                score[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(score, Collections.reverseOrder());
            int gap = 0;
            for(int j=1; j<n; j++) {
                gap = Math.max(gap, score[j-1] - score[j]);
            }
            System.out.println("Class " + (i+1));
            System.out.println("Max " + score[0] + ", Min " + score[score.length-1] + ", Largest gap " + gap);
        }
    }
}
