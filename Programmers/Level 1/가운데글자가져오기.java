package com.company;

public class 가운데글자가져오기 {
    public static String solution(String s) {
        String answer = "";
        int len = s.length();
        if(len %2 ==0) {
            answer = s.substring((len/2) -1, (len/2) +1);
        }
        else {
            answer = s.substring(len/2, (len/2) +1);
        }
        return answer;
    }
    public static void main(String[] args) {
        String s = "qwer";
        String result = solution(s);
        System.out.println("result = " + result);
    }
}
