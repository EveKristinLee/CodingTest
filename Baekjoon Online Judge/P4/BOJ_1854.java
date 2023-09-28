//BOJ P4 K번째 최단경로 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1854 {
    public static class City implements Comparable<City>{
        int idx;
        int cost;

        public City(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        public int compareTo(City c) {
            return this.cost - c.cost;
        }
    }

    static int N; //도시의 개수
    static int M; //도시간의 도로 개수
    static int K;
    static List<List<City>> graph;
    static List<Queue<Integer>> dist;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        dist = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
            dist.add(new PriorityQueue<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new City(b, c));
        }

        dijstra();
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++) {
            int d = -1;
            if(dist.get(i).size() >= K) {
                d = dist.get(i).peek() * -1;
            }
            sb.append(d).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijstra() {
        Queue<City> pq = new PriorityQueue<>();
        pq.offer(new City(1, 0));
        dist.get(1).add(0);

        while(!pq.isEmpty()) {
            City now = pq.poll();

            for(City next : graph.get(now.idx)) {
                if(dist.get(next.idx).size() < K) { //K개보다 작으면 무조건 넣기
                    dist.get(next.idx).add(-(now.cost + next.cost));
                    pq.offer(new City(next.idx, now.cost + next.cost));
                }
                else if((dist.get(next.idx).peek() * -1) > now.cost + next.cost) {
                    //K개인데, 더 작은 값이 생기면 갱신
                    dist.get(next.idx).poll();
                    dist.get(next.idx).add(-(now.cost + next.cost));
                    pq.offer(new City(next.idx, now.cost + next.cost));
                }
            }
        }
    }
}
