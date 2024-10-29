package solving.solve_0925;
//BOJ G3 2623 음악프로그램

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2623 {
    static int N; //가수의 수
    static int M; //보조피디의 수
    static List<List<Integer>> order;
    static int[] d;
    static List<Integer> res;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        order = new ArrayList<>();
        order.add(new ArrayList<>()); //0번째 채우기
        d = new int[N+1];
        for(int i=1; i<=N; i++) {
            order.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt-1; j++) {
                int now = Integer.parseInt(st.nextToken());
                order.get(before).add(now);
                d[now]++;
                before = now;
            }
        }

        res = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(d[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            Integer now = q.poll();
            res.add(now);

            for(int i=0; i<order.get(now).size(); i++) {
                Integer next = order.get(now).get(i);
                d[next]--;
                if(d[next] == 0) {
                    q.offer(next);
                }
            }
        }

        if(res.size() != N) {
            System.out.println(0);
        }
        else{
            for(int i=0; i<N; i++) {
                System.out.println(res.get(i));
            }
        }
    }
}
