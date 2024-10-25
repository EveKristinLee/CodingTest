//Programmers leve2 340212 [PCCP 기출문제] 퍼즐 게임 챌린지

import java.util.*;

public class PG_340212 {
    public static int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;

        int front = 1;
        int end = 100_001;
        while(front <= end) {
            int mid = (front + end) / 2;
            if(calcTime(diffs, times, limit, mid)) {
                end = mid - 1;
                answer = Math.min(answer, mid);
            }
            else {
                front = mid + 1;
            }
        }
        return answer;
    }

    private static boolean calcTime(int[] diffs, int[] times, long limit, int level) {
        long total = 0L;
        int len = diffs.length;
        for(int i=0; i<len; i++) {
            if(total > limit) {
                return false;
            }
            int diff = diffs[i];
            int time = times[i];
            //퍼즐을 맞춘 경우
            if(level >= diff) {
                total += time;
                continue;
            }
            else { //퍼즐을 틀린 경우
                int cnt = diff - level;
                int prev_time = 0;
                if(i > 0) {
                    prev_time = times[i-1];
                }
                int tmp = ((prev_time + time) * cnt) + time;
                total += tmp;
            }
        }

        if(total > limit) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] diffs = {1, 5, 3};
        int[] times = {2, 4, 7};
        long limit = 30;
        int res = solution(diffs, times, limit);
        System.out.println(res);
    }
}
