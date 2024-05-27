package com.company;
//BOJ S5 8892 팰린드롬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8892 {
    static int k;
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            boolean find = false;
            k = Integer.parseInt(br.readLine());
            words = new String[k];
            for(int i=0; i<k; i++) {
                words[i] = br.readLine();
            }

            for(int i=0; i<k; i++) {
                for(int j=0; j<k; j++) {
                    if(i!=j) {
                        String now = words[i] + words[j];
                        if(isPall(now)) {
                            sb.append(now).append("\n");
                            find = true;
                            break;
                        }
                    }
                }
                if(find) {
                    break;
                }
            }

            if(!find) {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isPall(String s) {
        int len = s.length();
        if(len % 2 == 0) {
            int a = (len / 2) -1;
            for(int i=0; i<=a; i++) {
                if(s.charAt(i) != s.charAt(len-i-1)) {
                    return false;
                }
            }
        }
        else {
            int a = len / 2;
            for(int i=0; i<a; i++) {
                if(s.charAt(i) != s.charAt(len-i-1)) {
                    return false;
                }
            }
        }

        return true;
    }
}
