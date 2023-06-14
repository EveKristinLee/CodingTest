import java.util.*;

public class 야근_지수 {
    public static long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<works.length; i++) {
            pq.offer(works[i]);
        }
        while(n-->0 && !pq.isEmpty()) {
            int top = pq.poll();
            if(top>1) {
                pq.offer(top-1);
            }
        }
        while(!pq.isEmpty()) {
            int top = pq.poll();
            answer += top * top;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] works = {4, 3, 3};
        long answer = solution(n, works);
        System.out.println(answer);
    }
}
