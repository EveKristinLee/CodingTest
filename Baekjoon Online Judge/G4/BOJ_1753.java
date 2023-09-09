//BOJ G4 최단경로
package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
    public static class Node implements Comparable<Node> {
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int V;
    static int E;
    static int K;
    static List<List<Node>> graph;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        dist = new int[V+1];
        for(int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        dijkstra();
        for(int i=1; i<=V; i++) {
            if(dist[i] == INF) {
                System.out.println("INF");
            }
            else{
                System.out.println(dist[i]);
            }
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[K] = 0;
        pq.offer(new Node(K, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(dist[now.idx] < now.weight) {
                continue;
            }

            for(int i=0; i<graph.get(now.idx).size(); i++) {
                Node next = graph.get(now.idx).get(i);
                if(dist[next.idx] > now.weight + next.weight) {
                    dist[next.idx] = now.weight + next.weight;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}
