package com.company;
//BOJ B4 25304 영수증

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int total = 0;
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            total += (cost * cnt);
        }

        if(X == total) {
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
}
