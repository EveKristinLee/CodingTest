package solving_2.solve_12.solve_19;
//BOJ G2 1202 보석 도둑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1202 {
    private static class Jewel implements Comparable<Jewel>{
        int idx;
        int w;
        int v;
        public Jewel(int idx, int w, int v) {
            this.idx = idx;
            this.w = w;
            this.v = v;
        }
        public int compareTo(Jewel j) {
            if(this.w == j.w) {
                return j.v - this.v;
            }
            return this.w - j.w;
        }
    }

    static int N;
    static int K;
    static int[] bag;
    static List<Jewel> jewel;
    static PriorityQueue<Jewel> pq;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K];
        jewel = new ArrayList<>();
        used = new boolean[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewel.add(new Jewel(i, w, v));
        }
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            bag[i] = Integer.parseInt(st.nextToken());
        }

        Collections.sort(jewel);
        Arrays.sort(bag);
        pq = new PriorityQueue<>(new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return o2.v - o1.v;
            }
        });
        long res = 0;
        int idx = 0;
        for(int i=0; i<K; i++) {
            while(idx < N) {
                if(bag[i] >= jewel.get(idx).w) {
                    pq.offer(new Jewel(jewel.get(idx).idx, jewel.get(idx).w, jewel.get(idx).v));
                    idx+=1;
                }
                else {
                    break;
                }
            }
            if(!pq.isEmpty()) {
                res += pq.poll().v;
            }
        }

        System.out.println(res);
    }
}
