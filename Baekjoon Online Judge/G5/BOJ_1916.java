package solving.solve_0914;
//BOJ G5 1916 최소비용 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916 {
    private static class Node implements Comparable<Node>{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    static int N;
    static int M;
    static List<List<Node>> link;
    static int[] d;
    static int Start;
    static int Dest;
    static final int INF = 1000000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        link = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            link.add(new ArrayList<>());
        }
        d = new int[N+1];
        Arrays.fill(d, INF);

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            link.get(s).add(new Node(e, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        Start = Integer.parseInt(st.nextToken());
        Dest = Integer.parseInt(st.nextToken());
        dijkstra(Start);
        System.out.println(d[Dest]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N+1];
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(!visit[now.idx]) {
                visit[now.idx] = true;
                for(int i=0; i<link.get(now.idx).size(); i++) {
                    Node next = link.get(now.idx).get(i);

                    if(d[next.idx] > d[now.idx] + next.cost) {
                        d[next.idx] = d[now.idx] + next.cost;
                        pq.offer(new Node(next.idx, d[next.idx]));
                    }
                }
            }
        }
    }
}
