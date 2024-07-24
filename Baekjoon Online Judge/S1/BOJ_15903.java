package solving;
//BOJ S1 15903 카드 합체 놀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903 {
    static int N;
    static int M;
    static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        while(M-->0) {
            long one = pq.poll();
            long two = pq.poll();
            long sum = one + two;

            pq.offer(sum);
            pq.offer(sum);
        }

        long res = 0;
        while(!pq.isEmpty()) {
            res += pq.poll();
        }
        System.out.println(res);
    }
}
