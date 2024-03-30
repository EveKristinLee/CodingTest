package solving.solve_1028;
//BOJ G4 10282 해킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {
    private static class Com implements Comparable<Com>{
        int idx;
        int time;

        public Com(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        public int compareTo(Com c) {
            if(this.time == c.time) {
                return this.idx - c.idx;
            }
            return this.time - c.time;
        }
    }
    static int n; //컴퓨터의 개수
    static int d; //의존성 개수
    static int c; //해킹당한 컴퓨터
    static List<List<Com>> link;
    static int[] dist;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            link = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                link.add(new ArrayList<>());
            }
            dist = new int[n+1];
            Arrays.fill(dist, INF);

            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                link.get(b).add(new Com(a, s));
            }

            dijkstra(c);
            int cnt = 0;
            int maxTime = Integer.MIN_VALUE;
            for(int i=1; i<=n; i++) {
                if(dist[i] != INF) {
                    cnt++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            sb.append(cnt).append(" ").append(maxTime).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int com) {
        PriorityQueue<Com> pq = new PriorityQueue<>();
        pq.offer(new Com(com, 0));
        dist[com] = 0;

        while(!pq.isEmpty()) {
            Com now = pq.poll();

            for(int i=0; i<link.get(now.idx).size(); i++) {
                Com next = link.get(now.idx).get(i);
                if(dist[next.idx] > dist[now.idx] + next.time) {
                    dist[next.idx] = dist[now.idx] + next.time;
                    pq.offer(new Com(next.idx, dist[next.idx]));
                }
            }
        }
    }
}
