package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;

public class OX퀴즈8958 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

	    int n;
        String str;

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            str = br.readLine();

            int sum = 0;
            int idx = 1;

            for(int j=0; j<str.length(); j++) {
                if(str.charAt(j) == 'O') {
                    sum += idx;
                    idx++;
                }
                else {
                    idx = 1;
                }
            }
            sb.append(sum + "\n");
        }
        System.out.println(sb);
        br.close();
    }
}
