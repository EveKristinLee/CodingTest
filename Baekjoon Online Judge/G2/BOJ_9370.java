//BOJ G2 미확인 도착지
package solving_2.solve_12.solve_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9370 {
    public static class Node implements Comparable<Node>{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static int n; //교차로 개수
    static int m; //도로 개수
    static int t; //목적지 후보 개수
    static int s; //출발지
    static int g; //지나간 교차로1
    static int h; //지나간 교차로2
    static List<List<Node>> graph; //양방향 도로
    static int[] candidate;
    static int[] dist;
    static int[] detour;
    static List<Integer> dest;
    static final int INF = 2_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            dist = new int[n+1];
            detour = new int[n+1];
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
                dist[i] = INF;
                detour[i] = INF;
            }
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }
            candidate = new int[t];
            for(int i=0; i<t; i++) {
                candidate[i] = Integer.parseInt(br.readLine());
            }
            dest = new ArrayList<>();

            dijkstra(s, dist);
            int second = (dist[g] > dist[h]) ? g : h;
            dijkstra(second, detour);
            for(int i=0; i<t; i++) {
                if(dist[second] + detour[candidate[i]] <= dist[candidate[i]]) {
                    dest.add(candidate[i]);
                }
            }
            Collections.sort(dest);
            for(int i=0; i<dest.size(); i++) {
                System.out.print(dest.get(i) + " ");
            }
            System.out.println();
        }
    }

    private static void dijkstra(int start, int[] d) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        d[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(d[now.idx] < now.cost) {
                continue;
            }

            for(int i=0; i<graph.get(now.idx).size(); i++) {
                Node next = graph.get(now.idx).get(i);
                if(d[next.idx] > now.cost + next.cost) {
                    d[next.idx] = now.cost + next.cost;
                    pq.offer(new Node(next.idx, d[next.idx]));
                }
            }
        }
    }
}