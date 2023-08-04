//BOJ S5
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 비밀번호_발음하기_4659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = "";
        while(!(s = br.readLine()).equals("end")) {
            sb.append("<").append(s).append("> is ");
            if(rule1(s) && rule2(s) && rule3(s)) {
                sb.append("acceptable.\n");
            }
            else {
                sb.append("not acceptable.\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean rule1(String s) {
        if(s.matches(".*[aeiou].*")) {
            return true;
        }
        return false;
    }

    private static boolean rule2(String s) {
        if(s.matches("(.*[aeiou]{3}.*)|.*[^aeiou]{3}.*")) {
            return false;
        }
        return true;
    }

    private static boolean rule3(String s) {
        Pattern pattern = Pattern.compile("(\\w)\\1");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            if(!(matcher.group(1).equals("e") || matcher.group(1).equals("o"))) {
                return false;
            }
        }
        return true;
    }
}
