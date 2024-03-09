package com.company;
//BOJ B4 31495 그게 무슨 코드니..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_31495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if(s.length() > 2) {
            if(s.charAt(0) == '\"' && s.charAt(s.length()-1) == '\"') {
                System.out.println(s.substring(1, s.length()-1));
            }
            else {
                System.out.println("CE");
            }
        }
        else {
            System.out.println("CE");
        }
    }
}
