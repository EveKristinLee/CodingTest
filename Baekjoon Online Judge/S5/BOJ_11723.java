package com.company;
//BOJ S5 11723 집합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int set = 0;
        StringBuilder sb = new StringBuilder();
        while(M-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String calc = st.nextToken();
            if(calc.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                set |= (1 << num);
            }
            else if(calc.equals("remove")) {
                int num = Integer.parseInt(st.nextToken());
                set &= ~(1 << num);
            }
            else if(calc.equals("check")) {
                int num = Integer.parseInt(st.nextToken());
                if((set & (1 << num)) == 0) {
                    sb.append("0").append("\n");
                }
                else {
                    sb.append("1").append("\n");
                }
            }
            else if(calc.equals("toggle")) {
                int num = Integer.parseInt(st.nextToken());
                set ^= (1 << num);
            }
            else if(calc.equals("all")) {
                set = (1 << 21) -1;
            }
            else if(calc.equals("empty")) {
                set = 0;
            }
        }

        System.out.println(sb);
    }
}
