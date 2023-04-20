package com.company;

import java.util.*;

public class 카드_뭉치 {
    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        boolean[] visit1 = new boolean[cards1.length];
        boolean[] visit2 = new boolean[cards2.length];
        int idx1 = 0;
        int idx2 = 0;
        boolean flag = true;
        for(int i=0; i<goal.length; i++) {
            if(idx1 < cards1.length && cards1[idx1].equals(goal[i])) {
                idx1++;
                continue;
            }
            if(idx2 < cards2.length && cards2[idx2].equals(goal[i])) {
                idx2++;
                continue;
            }
            flag = false;
            break;
        }
        if(flag) {
            answer = "Yes";
        }
        else {
            answer = "No";
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] cards1 = {"i", "drink", "water"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};
        System.out.println(solution(cards1, cards2, goal));
    }
}
