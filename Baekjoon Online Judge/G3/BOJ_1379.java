package solving.solve_0925;
//BOJ G3 1379 강의실 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1379 {
    private static class Class{
        int idx;
        int start;
        int end;

        public Class(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
    }

    private static class StartComparator implements Comparator<Class> {
        @Override
        public int compare(Class o1, Class o2) {
            return o1.start - o2.start;
        }
    }
    private static class EndComparator implements Comparator<Class> {
        @Override
        public int compare(Class o1, Class o2) {
            return o1.end - o2.end;
        }
    }
    static int N;
    static Class[] classes;
    static int[] order;
    static int K;
    static Queue<Class> q;
    static Queue<Class> room;
    static StringBuilder sb;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classes = new Class[N];
        q = new PriorityQueue<>(new StartComparator());
        room = new PriorityQueue<>(new EndComparator());
        order = new int[N+1];
        sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classes[i] = new Class(idx, start, end);
            q.offer(classes[i]);
        }

        K = 1;
        Class first = q.poll();
        room.add(first);
        order[first.idx] = K;

        while(!q.isEmpty()) {
            Class now = q.poll();
            int roomSize = room.size();
            boolean switchTime = false;
            for(int i=0; i<roomSize; i++) {
                if(room.peek().end <= now.start) {
                    Class before = room.poll();
                    order[now.idx] = order[before.idx];
                    room.offer(now);
                    switchTime = true;
                    break;
                }
            }
            if(!switchTime) {
                room.add(now);
                K++;
                order[now.idx] = K;
            }
        }

        sb.append(room.size()).append("\n");
        for(int i=1; i<=N; i++) {
            sb.append(order[i]).append("\n");
        }
        System.out.println(sb);
    }

}
