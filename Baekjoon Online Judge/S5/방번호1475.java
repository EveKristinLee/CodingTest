package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 방번호1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        int[] num = new int[11];
        for(int i=0; i<n.length(); i++) {
            num[n.charAt(i) - '0']++;
        }
        int nineSix = num[9] + num[6];
        if(nineSix % 2 == 0) {
            num[6] = nineSix / 2;
            num[9] = nineSix / 2;
        }
        else {
            num[6] = nineSix / 2 + 1;
            num[9] = nineSix / 2 + 1;
        }
        int cnt = Arrays.stream(num).max().getAsInt();
        System.out.println(cnt);
    }
}
