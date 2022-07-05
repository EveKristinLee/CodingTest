package com.company;

import java.util.Vector;

public class 모의고사 {
    public static Vector<Integer> solution(int[] answers) {
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = {0, 0, 0};

        Vector<Integer> answer = new Vector<Integer>();
        for(int i=0; i<answers.length; i++){
            if(answers[i] == one[i%5]) {
                score[0]++;
            }
            if(answers[i] == two[i%8]) {
                score[1]++;
            }
            if(answers[i] == three[i%10]) {
                score[2]++;
            }
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        for(int i=0; i<3; i++) {
            if(score[i] == maxScore) {
                answer.add(i+1);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Vector<Integer> result = solution(arr);
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }
}

